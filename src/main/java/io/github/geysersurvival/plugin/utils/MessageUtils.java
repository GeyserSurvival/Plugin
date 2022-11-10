package io.github.geysersurvival.plugin.utils;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.UUID;

public class MessageUtils {
    //The key will contain the sender's UUID, value will contain the UUID of the person to reply to
    public static HashMap<UUID, UUID> replyToList = new HashMap<>();

    public static @Nonnull String commandArgsToMessage(@Nonnull String[] args, int start) {
        //Combine the provided args into a single string
        StringBuilder message = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            message.append(args[i]).append(" ");
        }
        return message.toString();
    }
}
