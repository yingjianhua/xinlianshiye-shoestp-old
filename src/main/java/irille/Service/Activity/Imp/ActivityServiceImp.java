package irille.Service.Activity.Imp;

import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.*;
import irille.Dao.Activity.Romania.PkCompetitionDataDao;
import irille.Dao.Old.Activity.Romania.PkCompetitionDataDAO;
import irille.Dao.PdtProductDao;
import irille.Entity.Pk.PkCompetitionData;
import irille.Service.Activity.IActivityService;
import irille.pub.GoogleAnalytics.GoogleAnalyticsUtils;
import irille.pub.util.GetValue;
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


    @Override
    public PkCompetitionPageManageView getPkCompetitionData(Date startDate, Date endDate, Integer supId) {
        System.out.println(startDate);
        System.out.println(endDate);
        PkCompetitionPageManageView pkCompetitionPageManageView = new PkCompetitionPageManageView();
        Map all = pkCompetitionDataDao.getSupPk(startDate, endDate, supId);
        PkCompetitionData pkCompetitionData = new PkCompetitionData();
        pkCompetitionData.setPe(GetValue.get(all, "pe", BigDecimal.class, BigDecimal.ZERO).intValue());
        pkCompetitionData.setInquiry(GetValue.get(all, "inq", BigDecimal.class, BigDecimal.ZERO).intValue());
        pkCompetitionData.setTrafficvolume(GetValue.get(all, "tr", BigDecimal.class, BigDecimal.ZERO).intValue());


        PkCompetitionGlobalDataView globalDataView = new PkCompetitionGlobalDataView();
        globalDataView.setTop5(pkCompetitionDataDao.getTop5(startDate, endDate));
        globalDataView.setSum(pkCompetitionDataDao.getAllPe(startDate, endDate));


        pkCompetitionPageManageView.setPkCompetitionData(pkCompetitionData);
        pkCompetitionPageManageView.setPkCompetitionGlobalDataView(globalDataView);
        return pkCompetitionPageManageView;
    }

    /**
     * @Description: 一天执行一次  然后生成一次表单数据
     * @date 2018/12/3 20:00
     * @author lijie@shoestp.cn
     */
    @Override
    public void generateData() {
        String startDateString = pkCompetitionDataDao.getLastDate();
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate today = LocalDate.now();
        if (today.compareTo(startDate) < 1) {
            return;
        }
        AnalyticsReporting service = null;
        try {
            System.setProperty("http.proxySet", "true");
            System.setProperty("http.proxyHost", "127.0.0.1");
            System.setProperty("http.proxyPort", String.valueOf(1080));
// 针对https也开启代理
            System.setProperty("https.proxyHost", "127.0.0.1");
            System.setProperty("https.proxyPort", String.valueOf(1080));
            service = googleAnalyticsUtils.initializeAnalyticsReporting();
            // Create the DateRange object.
            DateRange dateRange = new DateRange();
            dateRange.setStartDate(startDateString);
            dateRange.setEndDate("today");  //TODO
            Metric sessions = new Metric()
                    .setExpression("ga:sessions")
                    .setAlias("sessions");
            Dimension adMatchedQuery = new Dimension().setName("ga:adMatchedQuery");
            Dimension path = new Dimension().setName("ga:pagePath");
            Dimension keyword = new Dimension().setName("ga:keyword");
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
            Map<Integer, GoogleAnalyticsView> map = new HashMap();
            for (Report report : response.getReports()) {
                ColumnHeader header = report.getColumnHeader();
                List<String> dimensionHeaders = header.getDimensions();
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
                    List<Integer> supplierId = Arrays.asList(281, 298, 283, 318, 279, 295, 16, 291, 282, 13, 317, 23, 78, 301);
                    int SupId = -1;
                    if (view.getType() == 4) {
                        SupId = pdtProductDao.getProductSupId(view.getId());
                    } else {
                        SupId = view.getId();
                    }

                    if (!supplierId.contains(SupId)) {
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
                        googleAnalyticsView.setInquiry(
                                pkCompetitionDataDao.getInquiry(Date.from(startDate.atStartOfDay().toInstant(ZoneOffset.UTC)), Date.from(today.atStartOfDay().toInstant(ZoneOffset.UTC)), SupId)
                        );
                    } else {
                        googleAnalyticsView.setTrafficVolume(googleAnalyticsView.getTrafficVolume() + trafficvolume);
                        googleAnalyticsView.setPe(googleAnalyticsView.getPe() + pe);
                        googleAnalyticsView.setInquiry(
                                pkCompetitionDataDao.getInquiry(Date.from(startDate.atStartOfDay().toInstant(ZoneOffset.UTC)), Date.from(today.atStartOfDay().toInstant(ZoneOffset.UTC)), SupId)
                        );
                    }
                    map.put(SupId, googleAnalyticsView);
                }
            }
            System.out.println(map);
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
        return googleAnalyticsView;
    }


}
