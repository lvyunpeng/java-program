import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-02 16:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})// 指定启动类
public class JpaTest {

//    @Autowired
//    private WealthRepository wealthRepository;
//
//    @Test
//    public void test(){
//        for(int i=0; i<100; i++){
//            Wealth wealth = wealthRepository.findByUserId(32);
//            System.out.println(i + ": " + wealth.getDiamonds());
//            wealth.setDiamonds(wealth.getDiamonds()-100);
//            wealthRepository.save(wealth);
//        }
//    }
}
