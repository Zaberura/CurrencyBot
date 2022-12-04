package org.zaberura.models;

import lombok.Builder;
import lombok.Data;
import org.zaberura.constants.ConversationState;

@Builder
@Data
public class UserSession {
    private long chatId;
    private String firstName;
    private ConversationState state;
    private String currency1;
    private String currency2;
    private String timeFrame;
    private String amount;
}
