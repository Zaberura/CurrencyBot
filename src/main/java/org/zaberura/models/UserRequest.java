package org.zaberura.models;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.zaberura.constants.ConversationState;

@Data
@Builder
public class UserRequest {
    private Update update;
    private long chatId;
    private UserSession userSession;
}
