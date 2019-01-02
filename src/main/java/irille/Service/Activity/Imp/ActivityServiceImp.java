package irille.Service.Activity.Imp;

import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.*;
import irille.Dao.Activity.Romania.PkCompetitionDataDao;
import irille.Dao.Old.Activity.Romania.PkCompetitionDataDAO;
import irille.Dao.PdtProductDao;
import irille.Entity.Pk.PkCompetitionData;
import irille.Service.Activity.IActivityService;
import irille.pub.GoogleAnalytics.GoogleAnalyticsUtils;
import irille.pub.util.GetValue1;
import irille.view.Activity.GoogleAnalyticsView;
import irille.view.Activity.PkCompetitionGlobalDataView;
import irille.view.Activity.PkCompetitionPageManageView;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/1
 * Time: 14:09
 */
public class ActivityServiceImp implements IActivityService {

    @Inject
    private GoogleAnalyticsUtils googleAnalyticsUtils;

    @Inject
    private PkCompetitionDataDao pkCompetitionDataDao;
    @Inject
    private PdtProductDao pdtProductDao;
    @Inject
    private PkCompetitionDataDAO pkCompetitionDataDAO;


    private AnalyticsReporting service = null;

    @Override
    public PkCompetitionPageManageView getPkCompetitionData(Date startDate, Date endDate, Integer supId, String type) {
        PkCompetitionPageManageView pkCompetitionPageManageView = new PkCompetitionPageManageView();
        if (type == null) type = "";
        PkCompetitionGlobalDataView globalDataView = new PkCompetitionGlobalDataView();

        switch (type) {
            case "trafficvolume": {
                globalDataView.setTop5(pkCompetitionDataDao.getTrafficvolumeTop5Count(startDate, endDate));
                Map map = pkCompetitionDataDao.getTrafficvolumeCount(startDate, endDate);
                if (map == null) {
                    globalDataView.setSum(0);
                } else {
                    Integer pe = Integer.valueOf(String.valueOf(map.get("trafficvolume")));
                    Integer count = Integer.valueOf(String.valueOf(map.get("count")));
                    globalDataView.setSum(pe / count);
                }
            }
            break;
            case "inquiry": {
                globalDataView.setTop5(pkCompetitionDataDao.getInqTop5Count(startDate, endDate));
                Map map = pkCompetitionDataDao.getInqCount(startDate, endDate);
                if (map == null) {
                    globalDataView.setSum(0);
                } else {
                    Integer pe = Integer.valueOf(String.valueOf(map.get("inquiry")));
                    Integer count = Integer.valueOf(String.valueOf(map.get("count")));
                    globalDataView.setSum(pe / count);
                }
            }
            break;
            default: {
                globalDataView.setTop5(pkCompetitionDataDao.getPeTop5Count(startDate, endDate));
                Map map = pkCompetitionDataDao.getAllPe(startDate, endDate);
                if (map == null) {
                    globalDataView.setSum(0);
                } else {
                    Integer pe = Integer.valueOf(String.valueOf(map.get("pe")));
                    Integer count = Integer.valueOf(String.valueOf(map.get("count")));
                    globalDataView.setSum(pe / count);
                }
            }
        }
        Map all = pkCompetitionDataDao.getSupPk(startDate, endDate, supId);
        PkCompetitionData pkCompetitionData = new PkCompetitionData();
        pkCompetitionData.setPe(GetValue1.get(all, "pe", BigDecimal.class, BigDecimal.ZERO).intValue());
        pkCompetitionData.setInquiry(GetValue1.get(all, "inq", BigDecimal.class, BigDecimal.ZERO).intValue());
        pkCompetitionData.setTrafficvolume(GetValue1.get(all, "tr", BigDecimal.class, BigDecimal.ZERO).intValue());

        pkCompetitionPageManageView.setPkCompetitionGlobalDataView(globalDataView);
        pkCompetitionPageManageView.setGoogleViewId(pkCompetitionDataDao.getGoogleViewId(supId));
        pkCompetitionPageManageView.setPkCompetitionData(pkCompetitionData);
        return pkCompetitionPageManageView;
    }

    /**
     * @Description: 一天执行一次  然后生成一次表单数据
     * @date 2018/12/3 20:00
     * @author lijie@shoestp.cn
     */
    @Override
    public void generateData() {
        //获取数据库里最后拉去数据的的日期
        String startDateString = pkCompetitionDataDao.getLastDate();
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate today = LocalDate.now();
        if (today.compareTo(startDate) < 1) {
            return;
        }
        try {
            //本地测试用开启代理
            System.setProperty("http.proxySet", "true");
            System.setProperty("http.proxyHost", "127.0.0.1");
            System.setProperty("http.proxyPort", String.valueOf(1080));
            System.setProperty("https.proxyHost", "127.0.0.1");
            System.setProperty("https.proxyPort", String.valueOf(1080));
            //初始化
            if (service == null) {
                service = googleAnalyticsUtils.initializeAnalyticsReporting();
            }
            DateRange dateRange = new DateRange();
            dateRange.setStartDate(startDateString);
            dateRange.setEndDate("today");  //TODO
            Metric sessions = new Metric()
                    .setExpression("ga:sessions")
                    .setAlias("sessions");
            Dimension adMatchedQuery = new Dimension().setName("ga:adMatchedQuery");
            Dimension path = new Dimension().setName("ga:pagePath");
            Dimension keyword = new Dimension().setName("ga:keyword");
//            ga:impressions  总展示次数
            //185165418总站的统计代码
            ReportRequest request = new ReportRequest()
                    .setViewId("185165418")
                    .setDateRanges(Arrays.asList(dateRange))
                    .setMetrics(Arrays.asList(sessions))
                    .setDimensions(Arrays.asList(path, adMatchedQuery, keyword));

            ArrayList<ReportRequest> requests = new ArrayList();
            requests.add(request);
            GetReportsRequest getReport = new GetReportsRequest()
                    .setReportRequests(requests);
            GetReportsResponse response = service.reports().batchGet(getReport).execute();
            Map<Integer, GoogleAnalyticsView> map = getPe();
            getInq(map, startDate, today);
            for (Report report : response.getReports()) {
                ColumnHeader header = report.getColumnHeader();
//                List<String> dimensionHeaders = header.getDimensions();
                List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
                List<ReportRow> rows = report.getData().getRows();
                if (rows == null) {
                    System.out.println("No data found");
                    return;
                }
                for (ReportRow row : rows) {
                    List<String> dimensions = row.getDimensions();
                    List<DateRangeValues> metrics = row.getMetrics();
                    GoogleAnalyticsView view = getId(dimensions.get(0));
                    if (view.getId() == null) {
                        continue;
                    }
                    int SupId = -1;
                    if (view.getType() == 4) {
                        SupId = pdtProductDao.getProductSupId(view.getId());
                    } else {
                        SupId = view.getId();
                    }

                    if (map.get(SupId) == null) {
                        continue;
                    }
                    String keyword2 = dimensions.get(1);
                    String keyword1 = dimensions.get(2);
                    int trafficvolume = 0;
                    int pe = 0;
                    for (int j = 0; j < metrics.size(); j++) {
                        DateRangeValues values = metrics.get(j);
                        for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
                            trafficvolume += Integer.valueOf(values.getValues().get(k));
                            if (!keyword2.equalsIgnoreCase("(not set)") || !keyword1.equalsIgnoreCase("(not set)")) {
                                pe += Integer.valueOf(values.getValues().get(k));
                            }
                        }
                    }
                    GoogleAnalyticsView googleAnalyticsView = map.get(SupId);
                    if (googleAnalyticsView == null) {
                        googleAnalyticsView = new GoogleAnalyticsView();
                        googleAnalyticsView.setId(SupId);
                        googleAnalyticsView.setTrafficVolume(trafficvolume);
                        googleAnalyticsView.setPe(pe);
                    } else {
                        googleAnalyticsView.setTrafficVolume(googleAnalyticsView.getTrafficVolume() + trafficvolume);
                        googleAnalyticsView.setPe(googleAnalyticsView.getPe() + pe);
                    }
                    map.put(SupId, googleAnalyticsView);
                }
            }
            map.forEach((integer, googleAnalyticsView) -> {
                PkCompetitionData pkCompetitionData = new PkCompetitionData();
                pkCompetitionData.setSupid(googleAnalyticsView.getId());
                pkCompetitionData.setPe(googleAnalyticsView.getPe());
                pkCompetitionData.setTrafficvolume(googleAnalyticsView.getTrafficVolume());
                pkCompetitionData.setInquiry(googleAnalyticsView.getInquiry());
                pkCompetitionDataDAO.setB(pkCompetitionData).commit();
            });

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @Description: 获取所有INQ
     * @date 2018/12/18 11:25
     * @author lijie@shoestp.cn
     */
    private void getInq(Map<Integer, GoogleAnalyticsView> map, LocalDate startDate, LocalDate today) {
        Integer[] integers = {281, 298, 283, 318, 279, 295, 16, 291, 282, 13, 317, 23, 78, 301, 165, 292};
        for (Integer integer : integers) {
            GoogleAnalyticsView googleAnalyticsView = map.get(integer);
            if (googleAnalyticsView == null) {
                googleAnalyticsView = new GoogleAnalyticsView();
            }
            googleAnalyticsView.setId(integer);
            googleAnalyticsView.setInquiry(
                    pkCompetitionDataDao.getInquiry(Date.from(startDate.atStartOfDay().toInstant(ZoneOffset.UTC)), Date.from(today.atStartOfDay().toInstant(ZoneOffset.UTC)), integer)
            );
            map.put(integer, googleAnalyticsView);
        }

    }

    /**
     * @Description: 获取URL数据
     * @date 2018/12/3 14:54
     * @author lijie@shoestp.cn
     */
    private GoogleAnalyticsView getId(String url) {
        GoogleAnalyticsView googleAnalyticsView = new GoogleAnalyticsView();

        if (url.indexOf("usr_UsrSupplier_gtSupIndex?pkey=") != -1) {
            googleAnalyticsView.setId(Integer.valueOf(url.substring((url.indexOf("pkey=") + 5))));
            googleAnalyticsView.setType(0);
        }
        if (url.indexOf("usr_UsrSupplier_gtSupPro?pkey=") != -1) {
            googleAnalyticsView.setId(Integer.valueOf(url.substring((url.indexOf("pkey=") + 5))));
            googleAnalyticsView.setType(1);
        }
        if (url.indexOf("usr_UsrSupplier_gtSupInfo?pkey=") != -1) {
            googleAnalyticsView.setId(Integer.valueOf(url.substring((url.indexOf("pkey=") + 5))));
            googleAnalyticsView.setType(2);
        }
        if (url.indexOf("usr_UsrSupplier_gtSupContact?pkey=") != -1) {
            googleAnalyticsView.setId(Integer.valueOf(url.substring((url.indexOf("pkey=") + 5))));
            googleAnalyticsView.setType(3);
        }
        if (url.indexOf("_p") != -1 && url.endsWith(".html")) {
            googleAnalyticsView.setId(Integer.valueOf(url.substring((url.lastIndexOf("_p") + 2), url.indexOf(".html"))));
            googleAnalyticsView.setType(4);
        }
        if (url.indexOf("prm_PrmGroupPurchase_getGroupPdt") != -1) {
            googleAnalyticsView.setId(Integer.valueOf(url.substring((url.indexOf("pkey=") + 5))));
            googleAnalyticsView.setType(4);
        }
        Pattern pattern = Pattern.compile("/activity/html/romania/(\\w+)[^/]?");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
            googleAnalyticsView.setType(0);
            switch (matcher.group(1)) {
                case "yilizhou": {
                    googleAnalyticsView.setId(23);
                }
                break;
                case "zhanhao": {
                    googleAnalyticsView.setId(301);
                }
                break;
                case "xinjiyuan": {
                    googleAnalyticsView.setId(317);
                }
                break;
                case "qianbaimeng": {
                    googleAnalyticsView.setId(282);
                }
                break;
                case "kangyida": {
                    googleAnalyticsView.setId(291);
                }
                break;
                case "kangrui": {
                    googleAnalyticsView.setId(16);
                }
                break;
                case "juna": {
                    googleAnalyticsView.setId(295);
                }
                break;
                case "jiekebaqiao": {
                    googleAnalyticsView.setId(78);
                }
                break;
                case "jiansha": {
                    googleAnalyticsView.setId(279);
                }
                break;
                case "huayou": {
                    googleAnalyticsView.setId(13);
                }
                break;
                case "fengsheng": {
                    googleAnalyticsView.setId(283);
                }
                break;
                case "disheng": {
                    googleAnalyticsView.setId(298);
                }
                break;
                case "baoluopate": {
                    googleAnalyticsView.setId(292);
                }
                break;
                case "allaifa": {
                    googleAnalyticsView.setId(281);
                }
                break;
            }
        }
        return googleAnalyticsView;
    }

    public Map getPe() {
        List<Map<String, Object>> list = pkCompetitionDataDao.getSupTraceCode();
        Map<Integer, GoogleAnalyticsView> result = new HashMap();
        list.forEach(stringObjectMap -> {
            String viewId = GetValue1.get(stringObjectMap, "viewId", String.class, "");
            int supId = GetValue1.get(stringObjectMap, "supId", Integer.class, -1);
            GoogleAnalyticsView googleAnalyticsView = new GoogleAnalyticsView();

            String startDateString = pkCompetitionDataDao.getLastDate();
            LocalDate startDate = LocalDate.parse(startDateString);
            LocalDate today = LocalDate.now();
            if (today.compareTo(startDate) < 1) {
                return;
            }
            try {
                DateRange dateRange = new DateRange();
                dateRange.setStartDate(startDateString);
                dateRange.setEndDate("today");  //TODO
                Metric sessions = new Metric()
                        .setExpression("ga:impressions");
                ReportRequest request = new ReportRequest()
                        .setViewId(viewId)
                        .setDateRanges(Arrays.asList(dateRange))
                        .setMetrics(Arrays.asList(sessions));

                ArrayList<ReportRequest> requests = new ArrayList();
                requests.add(request);
                GetReportsRequest getReport = new GetReportsRequest()
                        .setReportRequests(requests);
                GetReportsResponse response = service.reports().batchGet(getReport).execute();
                for (Report report : response.getReports()) {
                    ColumnHeader header = report.getColumnHeader();
                    List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
                    List<ReportRow> rows = report.getData().getRows();
                    if (rows == null) {
                        System.out.println("No data found");
                        continue;
                    }
                    for (ReportRow row : rows) {
                        List<DateRangeValues> metrics = row.getMetrics();
                        for (int j = 0; j < metrics.size(); j++) {
                            DateRangeValues values = metrics.get(j);
                            for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
                                googleAnalyticsView.setPe(Integer.valueOf(values.getValues().get(k)));
                                googleAnalyticsView.setInquiry(0);
                                googleAnalyticsView.setTrafficVolume(0);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            googleAnalyticsView.setId(supId);
            result.put(supId, googleAnalyticsView);

        });

        return result;
    }

}
