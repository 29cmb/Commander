package xyz.devcmb.commander;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Commander implements ModInitializer {
    public static Logger LOGGER = null;

    @Override
    public void onInitialize() {
        LOGGER = LoggerFactory.getLogger("commander");
    }
}
