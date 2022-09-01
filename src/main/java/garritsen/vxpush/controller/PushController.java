package garritsen.vxpush.controller;

/**
 *@ClassName PushController
 *@Description TODO
 *@Author Garritsen
 *@Date 2022/8/2 15:48
 */

import garritsen.vxpush.utils.Pusher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushController {
    //要推送的用户openid
    private static final String SOMEBODY = "xxxxxxxxxxxxxxxx";


    /**
     * 微信测试账号推送
     *
     */
    @GetMapping("/push")
    public void push() {
        Pusher.push(SOMEBODY);
    }

    /**
     * 微信测试账号推送
     * */
    @GetMapping("/push/{id}")
    public void pushId(@PathVariable("id") String id) {
        Pusher.push(id);
    }
}