package cloud.cinder.cindercloud.address.controller;

import cloud.cinder.cindercloud.address.controller.vo.AddressVO;
import cloud.cinder.cindercloud.address.service.AddressService;
import cloud.cinder.cindercloud.transaction.model.Transaction;
import cloud.cinder.cindercloud.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;
import rx.Observable;

import java.math.BigInteger;
import java.util.List;

import static cloud.cinder.cindercloud.utils.WeiUtils.format;

@Controller
@RequestMapping("/address")
public class AddressController {


    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AddressService addressService;

    @RequestMapping("/{hash}")
    public DeferredResult<ModelAndView> getAddress(@PathVariable("hash") final String hash) {
        final DeferredResult<ModelAndView> result = new DeferredResult<>();
        final ModelAndView modelAndView = new ModelAndView("addresses/address");
        final Observable<String> code = addressService.getCode(hash);
        final Observable<List<Transaction>> transactions = transactionService.findByAddress(hash).toList();
        final Observable<BigInteger> transactionCount = addressService.getTransactionCount(hash);
        final Observable<BigInteger> balance = addressService.getBalance(hash);
        Observable.zip(code, transactions, transactionCount, balance, (cde, tx, count, bal) -> {
            modelAndView.addObject("address", new AddressVO(cde, format(bal), count, tx));
            return modelAndView;
        }).subscribe(result::setResult);
        return result;
    }

}