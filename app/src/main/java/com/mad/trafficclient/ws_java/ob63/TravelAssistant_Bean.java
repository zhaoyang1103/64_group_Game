package com.mad.trafficclient.ws_java.ob63;


/**
 * Created by Go_Fight_Now on 2019/5/9 11:10
 */
public class TravelAssistant_Bean {
    private String touris_image;
    private String name;
    private Integer money;
    private String details_text;
    private Integer dianping;
    private String number;

    @Override
    public String toString() {
        return "TourismBean{" +
                "touris_image='" + touris_image + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", details_text='" + details_text + '\'' +
                ", dianping=" + dianping +
                ", number='" + number + '\'' +
                '}';
    }

    public TravelAssistant_Bean(String touris_image, String name, Integer money, String details_text, Integer dianping, String number) {
        this.touris_image = touris_image;
        this.name = name;
        this.money = money;
        this.details_text = details_text;
        this.dianping = dianping;
        this.number = number;
    }

    public String getDetails_text() {
        return details_text;
    }

    public void setDetails_text(String details_text) {
        this.details_text = details_text;
    }

    public Integer getDianping() {
        return dianping;
    }

    public void setDianping(Integer dianping) {
        this.dianping = dianping;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTouris_image() {
        return touris_image;
    }

    public void setTouris_image(String touris_image) {
        this.touris_image = touris_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

}
