package cloud.cinder.cindercloud.wallet.controller.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKeystoreCommand {

    private String password;
    private boolean secure = true;
}
