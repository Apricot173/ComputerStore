package com.apricot.store.Utils;

import java.util.HashMap;

/**
 * 本类用于维护一个哈希表，
 * 存储ProvinceName、CityName和AreaName
 * 和对应ProvinceCode、CityCode和AreaCode。
 * 从而可以根据省市区名称获取对应的省市区代码。
 */
public class CodeNameGenerator {
    HashMap<String, String> mapProvince = new HashMap<>();
    HashMap<String, String> mapCity = new HashMap<>();
    HashMap<String, String> mapArea = new HashMap<>();
    public CodeNameGenerator() {
        mapProvince.put("北京市", "110000");
        mapProvince.put("天津市", "120000");
        mapProvince.put("河北省", "130000");
        mapProvince.put("山西省", "140000");
        mapCity.put("石家庄市", "130100");
        mapCity.put("保定市", "130600");
        mapCity.put("唐山市", "130200");
        mapCity.put("秦皇岛市", "130300");
        mapArea.put("长安区", "130102");
        mapArea.put("桥西区", "130104");
        mapArea.put("新华区", "130105");
        mapArea.put("井陉矿区", "130107");
        mapArea.put("裕华区", "130108");
    }

    public String getP(String provinceName) {
        if (mapProvince.containsKey(provinceName)) {
            return mapProvince.get(provinceName);
        }
        return "999999";
    }

    public String getC(String cityName) {
        if (mapCity.containsKey(cityName)) {
            return mapCity.get(cityName);
        }
        return "888888";
    }

    public String getA(String areaName) {
        if (mapArea.containsKey(areaName)) {
            return mapArea.get(areaName);
        }
        return "777777";
    }
}
