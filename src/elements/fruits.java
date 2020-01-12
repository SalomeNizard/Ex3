package elements;

import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;
import utils.StdDraw;

import java.util.List;

public class fruits implements Fruits_I {
    private double value;
    private int type;
    private Point3D pos;
    private String image;

    // constructor
    public fruits(){
        this.value = 0;
        this.type = 0;
        this.pos = null;
        this.image = null;
    }

    /**
     * init fruit from json file
     * @param Jstr
     * @return
     */
    public Fruits_I init(String Jstr){
        fruits fruit = new fruits();
        try {
            JSONObject fruits = new JSONObject(Jstr);
            JSONObject f = new JSONObject("Fruit");
            fruit.value = f.getDouble("value");
            fruit.type = f.getInt("type");
            String location_str = f.getString("pos");
            fruit.pos = new Point3D(location_str);
            if(fruit.type == 1){
                fruit.image = "apple.png";
            }else{
                fruit.image = "banana.png";
                StdDraw.picture(fruit.pos.x() , fruit.pos.y() , "banana.png");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fruit;
    }

    public void drawFruits(List<fruits> fruitList){
        for(fruits f : fruitList){
            StdDraw.picture(f.pos.x() , f.pos.y() , f.image , 0.001 , 0.001);
        }
    }

}