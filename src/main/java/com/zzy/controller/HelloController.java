package com.zzy.controller;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Thinkpad-W530 on 2021/10/22.
 */

@RestController
@RequestMapping("/test")
public class HelloController {


    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/mono")
    public Mono<Object> mono() {
        return Mono.create(monoSink -> {
            LOGGER.info("创建 Mono");
            monoSink.success("hello webflux");
        }).doOnSubscribe(subscription -> { //当订阅者去订阅发布者的时候，该方法会调用
            LOGGER.info("{}", subscription.hashCode());
        }).doOnNext(o -> { //当订阅者收到数据时，改方法会调用
            LOGGER.info("{}", o);
        });
    }


    @GetMapping(value = "/flux")
    public Flux<Object> flux() {
        return Flux.generate(
                () -> 1,
                (i, sink) -> {
                    LOGGER.warn("i:{}", i);
                    sink.next(i * i + "");
                    if (i == 5) sink.complete();
                    return ++i;
                },
                state -> LOGGER.warn("the final state is:{}", state)
        );
    }


    @GetMapping(value = "/flux2", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Object> flux2() {


            return Flux.create(sink -> {

                for (int i = 0; i < 10000; i++) {

                    sink.next("{\"code\":\"0\",\"data\":{\"pageNum\":\"1\",\"pageSize\":\"10\",\"size\":\"9\",\"orderBy\":\"\",\"startRow\":\"1\",\"endRow\":\"9\",\"total\":\"9\",\"pages\":\"1\",\"list\":[{\"qid\":\"f09626fa7bdb48f1b3c0be807e558c55\",\"areacode\":\"wx,hs\",\"qname\":\"大脖子送气有限公司1\",\"xydm\":\"XHFJFKE122\",\"qleader\":\"秦小博11\",\"qaddress\":\"测试地址\",\"qtel\":\"18552\",\"lname\":\"脖子大\",\"ltel\":\"2222\",\"lng\":\"120.320480\",\"lat\":\"31.530222\",\"areaname\":\"无锡市,惠山区\",\"orders\":\"1\",\"bm\":\"2222\",\"remark\":\"测试备注\",\"creater\":\"张振宇\",\"modifyer\":\"张振宇\",\"createdate\":\"2021-10-08 09:51:52\",\"modifydate\":\"2021-10-08 10:22:27\",\"qint\":\"5ZOI5ZOI5ZOI5ZOI5ZOI5ZOI5rWL6K+V5LiA5LiLMTIz\",\"qval\":\"哈哈哈哈哈哈测试一下123\",\"fileRelList\":\"\",\"entImg\":[{\"id\":\"d362cc5961ac4372bb45df625b193a42\",\"basename\":\"d623943ce986428ea2af3e5980748594.jpg\",\"fileurl\":\"\\\\f366b06b151b4b50a0f6d3a3e5a00846.jpg\",\"parentId\":\"124205\",\"creater\":\"7126fb43aa2a411ea29574e23d69ec12\",\"modifyer\":\"7126fb43aa2a411ea29574e23d69ec12\",\"createdate\":\"1633656696000\",\"updatedate\":\"1633656696000\",\"fsize\":\"24774\",\"pdfpath\":\"\",\"filetype\":\"img\",\"status\":\"\",\"ittype\":\"\",\"size\":\"24774\"}]},{\"qid\":\"b97d2b0cc1374b03b09ce8b71687d1ca\",\"areacode\":\"wx\",\"qname\":\"无锡市联丰燃气有限公司\",\"xydm\":\"\",\"qleader\":\"\",\"qaddress\":\"无锡惠山经济开发区堰新路311号3号楼0612-1室\",\"qtel\":\"\",\"lname\":\"徐国彪\",\"ltel\":\"0510—88300789\",\"lng\":\"\",\"lat\":\"\",\"areaname\":\"无锡市\",\"orders\":\"1\",\"bm\":\"联丰\",\"remark\":\"\",\"creater\":\"\",\"modifyer\":\"\",\"createdate\":\"\",\"modifydate\":\"\",\"qint\":\"\",\"qval\":\"\",\"fileRelList\":\"\",\"entImg\":[]},{\"qid\":\"4028814666d3cede0166d459061b0007\",\"areacode\":\"wx\",\"qname\":\"无锡市马山气体有限公司\",\"xydm\":\"\",\"qleader\":\"\",\"qaddress\":\"无锡市滨湖区马山乐山路108号\",\"qtel\":\"\",\"lname\":\"朱锡林\",\"ltel\":\"0510-85996396\",\"lng\":\"\",\"lat\":\"\",\"areaname\":\"无锡市\",\"orders\":\"2\",\"bm\":\"马山\",\"remark\":\"\",\"creater\":\"\",\"modifyer\":\"\",\"createdate\":\"\",\"modifydate\":\"\",\"qint\":\"\",\"qval\":\"\",\"fileRelList\":\"\",\"entImg\":[]},{\"qid\":\"4028814666d3cede0166d45a9e12000b\",\"areacode\":\"wx\",\"qname\":\"无锡华润燃气有限公司\",\"xydm\":\"\",\"qleader\":\"\",\"qaddress\":\"无锡市金石东路393号\",\"qtel\":\"\",\"lname\":\"宋晓枫\",\"ltel\":\"0510-83232567\",\"lng\":\"\",\"lat\":\"\",\"areaname\":\"无锡市\",\"orders\":\"3\",\"bm\":\"华润\",\"remark\":\"\",\"creater\":\"\",\"modifyer\":\"\",\"createdate\":\"\",\"modifydate\":\"\",\"qint\":\"\",\"qval\":\"\",\"fileRelList\":\"\",\"entImg\":[]},{\"qid\":\"4028814666d3cede0166d45bfd0f000e\",\"areacode\":\"wx\",\"qname\":\"无锡百地年液化石油气有限公司\",\"xydm\":\"\",\"qleader\":\"\",\"qaddress\":\"无锡市惠山区西漳工业园区\",\"qtel\":\"\",\"lname\":\"殷永祥\",\"ltel\":\"0510-88551517\",\"lng\":\"\",\"lat\":\"\",\"areaname\":\"无锡市\",\"orders\":\"4\",\"bm\":\"百地年\",\"remark\":\"\",\"creater\":\"\",\"modifyer\":\"\",\"createdate\":\"\",\"modifydate\":\"\",\"qint\":\"\",\"qval\":\"\",\"fileRelList\":\"\",\"entImg\":[]},{\"qid\":\"4028814666d3cede0166d45dbc510013\",\"areacode\":\"wx\",\"qname\":\"无锡市华盛气体有限公司\",\"xydm\":\"\",\"qleader\":\"\",\"qaddress\":\"无锡市锡山区昆凌路3号\",\"qtel\":\"\",\"lname\":\"邱国华\",\"ltel\":\"0510-88700729\",\"lng\":\"\",\"lat\":\"\",\"areaname\":\"无锡市\",\"orders\":\"5\",\"bm\":\"华盛\",\"remark\":\"\",\"creater\":\"\",\"modifyer\":\"\",\"createdate\":\"\",\"modifydate\":\"\",\"qint\":\"\",\"qval\":\"\",\"fileRelList\":\"\",\"entImg\":[]},{\"qid\":\"d9eb9202b3ed47eda543e32cff1736be\",\"areacode\":\"wx\",\"qname\":\"荣盛达（无锡）能源有限公司\",\"xydm\":\"\",\"qleader\":\"\",\"qaddress\":\"\",\"qtel\":\"\",\"lname\":\"\",\"ltel\":\"\",\"lng\":\"\",\"lat\":\"\",\"areaname\":\"无锡市\",\"orders\":\"6\",\"bm\":\"荣盛达\",\"remark\":\"\",\"creater\":\"\",\"modifyer\":\"\",\"createdate\":\"\",\"modifydate\":\"\",\"qint\":\"\",\"qval\":\"\",\"fileRelList\":\"\",\"entImg\":[]},{\"qid\":\"cf85302cabf64750876ca3ddfd571023\",\"areacode\":\"wx\",\"qname\":\"无锡苏新天然气利用有限公司\",\"xydm\":\"\",\"qleader\":\"\",\"qaddress\":\"\",\"qtel\":\"\",\"lname\":\"\",\"ltel\":\"\",\"lng\":\"\",\"lat\":\"\",\"areaname\":\"无锡市\",\"orders\":\"7\",\"bm\":\"苏新\",\"remark\":\"\",\"creater\":\"\",\"modifyer\":\"\",\"createdate\":\"\",\"modifydate\":\"\",\"qint\":\"\",\"qval\":\"\",\"fileRelList\":\"\",\"entImg\":[]},{\"qid\":\"f75710da3b364c688671549dbf6f1f8c\",\"areacode\":\"wx,xs,xs01,dt01\",\"qname\":\"测试111\",\"xydm\":\"\",\"qleader\":\"\",\"qaddress\":\"\",\"qtel\":\"\",\"lname\":\"\",\"ltel\":\"\",\"lng\":\"\",\"lat\":\"\",\"areaname\":\"无锡市,锡山区,东亭街道,华亭社区\",\"orders\":\"11\",\"bm\":\"\",\"remark\":\"\",\"creater\":\"王川\",\"modifyer\":\"王川\",\"createdate\":\"2021-10-20 10:50:56\",\"modifydate\":\"2021-10-20 14:07:58\",\"qint\":\"5rWL6K+V5pS25Yiw6LSnaWblpKfluIjlgoXnmoTor7Tms5XnmoTlkIjms5XnmoTlj4zmlrnpg73mmK/nsonnuqLnmoTmiYvmnLrlj7fpmYTku7bnrKzkuInmtYvor5XmlLbliLDotKdpZuWkp+W4iOWCheeahOivtOazleeahOWQiOazleeahOWPjOaWuemDveaYr+eyiee6oueahOaJi+acuuWPt+mZhOS7tuesrOS4iea1i+ivleaUtuWIsOi0p2lm5aSn5biI5YKF55qE6K+05rOV55qE5ZCI5rOV55qE5Y+M5pa56YO95piv57KJ57qi55qE5omL5py65Y+36ZmE5Lu256ys5LiJ5rWL6K+V5pS25Yiw6LSnaWblpKfluIjlgoXnmoTor7Tms5XnmoTlkIjms5XnmoTlj4zmlrnpg73mmK/nsonnuqLnmoTmiYvmnLrlj7fpmYTku7bnrKzkuInmtYvor5XmlLbliLDotKdpZuWkp+W4iOWCheeahOivtOazleeahOWQiOazleeahOWPjOaWuemDveaYr+eyiee6oueahOaJi+acuuWPt+mZhOS7tuesrOS4iQ==\",\"qval\":\"测试收到货if大师傅的说法的合法的双方都是粉红的手机号附件第三测试收到货if大师傅的说法的合法的双方都是粉红的手机号附件第三测试收到货if大师傅的说法的合法的双方都是粉红的手机号附件第三测试收到货if大师傅的说法的合法的双方都是粉红的手机号附件第三测试收到货if大师傅的说法的合法的双方都是粉红的手机号附件第三\",\"fileRelList\":\"\",\"entImg\":[]}],\"firstPage\":\"1\",\"prePage\":\"0\",\"nextPage\":\"0\",\"lastPage\":\"1\",\"isFirstPage\":true,\"isLastPage\":true,\"hasPreviousPage\":false,\"hasNextPage\":false,\"navigatePages\":\"8\",\"navigatepageNums\":[\"1\"]},\"desc\":\"成功\"}");
                }
                sink.complete();
            }).doOnSubscribe(subscription -> { //当订阅者去订阅发布者的时候，该方法会调用
                        LOGGER.info("{}", subscription);
                    }
            ).doOnNext(o -> { //当订阅者收到数据时，改方法会调用
                LOGGER.info("{}", o);
            });


    }

}
