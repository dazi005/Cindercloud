package cloud.cinder.cindercloud.abi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AbiContractFallback extends AbiContractElement {

    private String stateMutability;
    private boolean payable;

}
