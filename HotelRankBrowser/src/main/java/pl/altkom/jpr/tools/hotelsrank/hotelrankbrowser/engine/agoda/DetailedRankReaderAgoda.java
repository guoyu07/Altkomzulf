package pl.altkom.jpr.tools.hotelsrank.hotelrankbrowser.engine.agoda;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.altkom.jpr.tools.hotelsrank.hotelrankbrowser.engine.HotelDetailedRankReader;
import pl.altkom.jpr.tools.hotelsrank.hotelrankbrowser.engine.model.DetailedRank;

/**
 *
 * @author Marcin Lis
 *
 */
public class DetailedRankReaderAgoda implements HotelDetailedRankReader {

    private Document doc;

    public DetailedRank readDetailedRank(String hotelUrl) {

        try {
            if (doc == null) {
                doc = Jsoup.connect(hotelUrl).userAgent("Mozilla").get();
            }
            Elements values = doc.select("#ctl00_ctl00_MainContent_ContentMain_HotelReview1_lblFood");
            DetailedRank rank = new DetailedRank();

            for (Element val : values) {

                rank.setFood(toFloat(val.text()));
            }

            Elements values1 = doc.select("#ctl00_ctl00_MainContent_ContentMain_HotelReview1_lblLocation");
            for (Element val : values1) {

                rank.setLoacalization(toFloat(val.text()));
            }

            Elements values2 = doc.select("#ctl00_ctl00_MainContent_ContentMain_HotelReview1_lblValueOfMoney");
            for (Element val : values2) {

                rank.setWorthPrice(toFloat(val.text()));
            }

            Elements values3 = doc.select("#ctl00_ctl00_MainContent_ContentMain_HotelReview1_lblRoomComfort");
            for (Element val : values3) {

                rank.setRoomComfort(toFloat(val.text()));
            }

            Elements values4 = doc.select("#ctl00_ctl00_MainContent_ContentMain_HotelReview1_lblHotelCond");
            for (Element val : values4) {

                rank.setWorkers(toFloat(val.text()));
            }

            Elements values5 = doc.select("#ctl00_ctl00_MainContent_ContentMain_HotelReview1_lblStaff");
            for (Element val : values5) {

                rank.setQuality(toFloat(val.text()));
            }
            return rank;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDocument(Document doc) {
        this.doc = doc;

    }
    private float toFloat(String  str) throws java.lang.NumberFormatException{
           String number = str.replace(",", ".");
           return Float.parseFloat(number);
    }

}
