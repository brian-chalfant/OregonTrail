import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import sun.audio.*;

public class PlaySound {
    public static PlaySound playSound = new PlaySound("resources/game_data");
    private HashMap<String, Object> data = new HashMap<>();


    /**
     * usage PlaySound.play("Key that is in JSON File")
     * JSON file located in resources/game_data
     * @param key - Key in JSON file related to Path of Sound File
     */
    public static void play(String key) {
        String path = PlaySound.getString(key);

        InputStream in;
        try {
            in = Files.newInputStream(Paths.get(path));

            AudioStream audioStream = new AudioStream(in);

            AudioPlayer.player.start(audioStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public PlaySound(String jsonDirectory) {
        File jsonDir = new File(jsonDirectory);

        for (File jsonFile : jsonDir.listFiles()) {
            HashMap<String, Object> obj = Utils.readJSON(jsonFile);
            for (Object key : obj.keySet()) {
                data.put((String) key, obj.get(key));
            }
        }
    }


    public static String getString(String key) {
        return (String) playSound.data.getOrDefault(key, null);
    }

}