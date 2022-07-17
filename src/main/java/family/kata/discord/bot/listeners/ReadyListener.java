package family.kata.discord.bot.listeners;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReadyListener implements EventListener {
    private final Logger logger
            = LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        logger.debug("Got Ready event");
    }
}
