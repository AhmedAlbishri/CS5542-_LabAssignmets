import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.typography.hershey.HersheyFont;

import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ImageAnnotation {
    public static void main(String[] args) throws IOException {
        final ClarifaiClient client = new ClarifaiBuilder("App-Key", "Secret-Key")
                .client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
                .buildSync(); // or use .build() to get a Future<ClarifaiClient>
        client.getToken();

        File file = new File("output/mainframes");
        File[] files = file.listFiles();
        List<String> sumarry = new ArrayList<String>();
        HashMap<String, Float> sum = new HashMap<String, Float>();


        for (int i=0; i<files.length;i++){
            ClarifaiResponse response = client.getDefaultModels().generalModel().predict()
                    .withInputs(
                            ClarifaiInput.forImage(ClarifaiImage.of(files[i]))
                    )
                    .executeSync();
            List<ClarifaiOutput<Concept>> predictions = (List<ClarifaiOutput<Concept>>) response.get();
            MBFImage image = ImageUtilities.readMBF(files[i]);
            int x = image.getWidth();
            int y = image.getHeight();

            System.out.println("*************" + files[i] + "***********");
            List<Concept> data = predictions.get(0).data();
            for (int j = 0; j < data.size(); j++) {
                sumarry.add(data.get(j).name());

                sum.put(data.get(j).name(), data.get(j).value()+1);

                System.out.println(data.get(j).name() + " - " + data.get(j).value());
                image.drawText(data.get(j).name(), (int)Math.floor(Math.random()*x), (int) Math.floor(Math.random()*y), HersheyFont.ASTROLOGY, 20, RGBColour.RED);
            }
            //DisplayUtilities.displayName(image, "image" + i);

        }

       // System.out.println("This video contain  "+ sum);

        HashMap<String, Integer> Hmap = new HashMap<String, Integer>();

        for (String string :sumarry ) {

            if(Hmap.keySet().contains(string))
            {
                Hmap.put(string, Hmap.get(string)+1);

            }else
            {
                Hmap.put(string, 1);
            }
        }

        Set<Entry<String, Float>> set = sum.entrySet();
        List<Entry<String, Float>> list = new ArrayList<Entry<String, Float>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Float>>()
        {
            public int compare( Map.Entry<String, Float> o1, Map.Entry<String, Float> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        System.out.println("\n***** The Video Summary *****\n");
        for(Map.Entry<String, Float> entry:list){
            System.out.println("This video contains "+ Hmap.get(entry.getKey()) +" "+ entry.getKey() +" with this total weight "+ entry.getValue());
        }

    }

}
