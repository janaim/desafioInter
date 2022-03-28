import com.example.desafioInter.entity.Investimento;
import com.example.desafioInter.repository.InvestimentoRepository;
import com.example.desafioInter.service.InvestimentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
public class InvestimentoTest {

    @Mock
    private InvestimentoRepository investimentoRepoMock;

    @InjectMocks
    private InvestimentoService investimentoServiceMock;

    @Test
    public void criarNovoInvestimentoPorCliente(){

    }


}
