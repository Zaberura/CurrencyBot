package org.zaberura.models;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
@Builder
public class UserRequest {
    private Update update;
    private long chatId;
    //private UserSession userSession;
}
