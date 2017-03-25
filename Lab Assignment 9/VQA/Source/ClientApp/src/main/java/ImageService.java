import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Naga on 13-03-2017.
 */
@WebServlet(name = "ImageService", urlPatterns = "/ImageService")
public class ImageService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String response="";
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println(data);
        String output = "";
        JSONObject params = new JSONObject(data);
        JSONObject result = params.getJSONObject("result");
        JSONObject parameters = result.getJSONObject("parameters");
        if (parameters.get("transportation").toString().equals("transportation")) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("https://www.transportation.gov/sites/dot.gov/files/pictures/Multimodalism.jpg");
            jsonArray.put("http://www.rishabhsoft.com/wp-content/uploads/2013/08/Transportation.jpg");
            jsonArray.put("http://gisgeography.com/wp-content/uploads/2015/01/transportation-global-maps1.png");
            jsonObject.put("data", jsonArray);
            output = jsonObject.toString();
            Data data_ob = Data.getInstance();
            data_ob.setData(output);
            data_ob.setFlag(true);
            JSONObject js = new JSONObject();
            js.put("speech", "transportation are displayed");
            js.put("displayText", "transportation are displayed");
            js.put("source", "image database");
            response = js.toString();
        }
        else if (parameters.get("transportation").toString().equals("cars")) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("http://img.autobytel.com/car-reviews/autobytel/11694-good-looking-sports-cars/2016-Ford-Mustang-GT-burnout-red-tire-smoke.jpg");
            jsonArray.put("http://blog.caranddriver.com/wp-content/uploads/2015/11/BMW-2-series.jpg");
            jsonArray.put("https://s-media-cache-ak0.pinimg.com/originals/0f/fa/0b/0ffa0b1f5b61ef2b0b182c7df413d448.jpg");
            jsonArray.put("https://cms.kelleybluebookimages.com/content/dam/kbb-editorial/10-best-lists/top-10s/10-best-luxury-cars-under-35000/2015-Mercedes-Benz-GLA-Class-best-luxury-cars-under-35000.jpg");
            jsonObject.put("data", jsonArray);
            output = jsonObject.toString();
            Data data_ob = Data.getInstance();
            data_ob.setData(output);
            data_ob.setFlag(true);
            JSONObject js = new JSONObject();
            js.put("speech", "cars are displayed");
            js.put("displayText", "cars are displayed");
            js.put("source", "image database");
            response = js.toString();
        }
        else if (parameters.get("transportation").toString().equals("trains")){
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("http://www.soundtransit.org/sites/default/files/styles/hero/public/images/rider_guide/sounder/hero_sounder_455.jpg?itok=71cTGqvN");
            jsonArray.put("http://www.ouest-france.fr/sites/default/files/styles/image-640x360/public/2013/09/27/train.le-rail-francais-deuxieme-meilleur-deurope.jpg?itok=60iPM445");
            jsonArray.put("http://i2.cdn.cnn.com/cnnnext/dam/assets/161103111254-german-zero-emission-train-large-169.jpg");
            jsonObject.put("data", jsonArray);
            output = jsonObject.toString();
            Data data_ob = Data.getInstance();
            data_ob.setData(output);
            data_ob.setFlag(true);
            JSONObject js = new JSONObject();
            js.put("speech", "trains are displayed");
            js.put("displayText", "trains are displayed");
            js.put("source", "image database");
            response = js.toString();
        }
        else if (parameters.get("transportation").toString().equals("airplanes")){
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("https://vid.alarabiya.net/images/2013/07/19/5c61c865-9ccb-43c2-8ced-9ce3b5eeacd3/5c61c865-9ccb-43c2-8ced-9ce3b5eeacd3_16x9_600x338.jpg");
            jsonArray.put("http://www.arabnews.com/sites/default/files/styles/ph3_660_400/public/2016/02/23/file-23-Saudia%20Airlines_0.jpg");
            jsonArray.put("http://www.pakijobs.pk/wp-content/uploads/2016/04/Saudi-Airline-Hajj-Flight-Schedule-2016-Lahore-Karachi-Peshawar-Faisalabad.jpg");
            jsonObject.put("data", jsonArray);
            output = jsonObject.toString();
            Data data_ob = Data.getInstance();
            data_ob.setData(output);
            data_ob.setFlag(true);
            JSONObject js = new JSONObject();
            js.put("speech", "airplanes are displayed");
            js.put("displayText", "airplanes are displayed");
            js.put("source", "image database");
            response = js.toString();
        }
        else if (parameters.get("null").toString().equals("clear")){
            Data data_ob = Data.getInstance();
            JSONObject js1 = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(" ");
            js1.put("data", jsonArray);
            data_ob.setData(js1.toString());
            data_ob.setFlag(true);
            JSONObject js = new JSONObject();
            js.put("speech", "screen is cleared");
            js.put("displayText", "screen is cleared");
            js.put("source", "image database");
            response = js.toString();
        }
        resp.setHeader("Content-type", "application/json");
        resp.getWriter().write(response);
    }
}
