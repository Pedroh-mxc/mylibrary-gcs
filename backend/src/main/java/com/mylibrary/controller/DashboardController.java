@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;

    @GetMapping
    public Map<String, Long> dashboard() {

        Map<String, Long> dados = new HashMap<>();

        dados.put(
                "totalLivros",
                livroRepository.count()
        );

        dados.put(
                "emprestados",
                livroRepository.countByStatus(
                        StatusLivro.EMPRESTADO
                )
        );

        dados.put(
                "disponiveis",
                livroRepository.countByStatus(
                        StatusLivro.DISPONIVEL
                )
        );

        dados.put(
                "emprestimosAtivos",
                emprestimoRepository
                        .countByDataDevolucaoEfetivaIsNull()
        );

        return dados;
    }
}