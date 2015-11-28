package quentillionaire.dddminimap.Loaders;

import com.google.common.base.Stopwatch;
import net.minecraftforge.common.config.Configuration;
import quentillionaire.dddminimap.Utillity.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ConfigLoader {

    public static void init(File file) {
        String spacing = "  ";
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Loading configs started");
                Configuration config = new Configuration(file);
                Logger.info(spacing + "Reading " + config);
                    config.load();
                        //Config code here
                    config.save();
            Logger.info(spacing + "Saving " + config);
        Logger.info("Loading configs finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
}
