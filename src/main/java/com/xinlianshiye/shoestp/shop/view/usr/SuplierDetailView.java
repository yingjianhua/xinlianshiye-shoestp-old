package com.xinlianshiye.shoestp.shop.view.usr;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class SuplierDetailView implements BaseView {
    private Integer pkey;//卖家pkey
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String companyName;//公司名称
    private String logo;//LOGO
    private Byte SVSGRade;//SVS等级
}
