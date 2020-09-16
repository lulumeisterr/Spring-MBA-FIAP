# Spring-MBA-FIAP


# ConditionalBean

  - Nem sempre vamos querer que uma classe seja instanciada ou um endpoint fique disponivel entao podemos dizer com quais condicoes
  esse endpoint pode ser acessado podendo ser por properties etc ..
    Exemplo de uso : Voce pode ter uma funcionalide rodando em java 11 porem vc usa o java 7 , entao vc pode habilitar aquela funcao apenas para java 11

              @RestController
              @RequestMapping("debug")
              @ConditionalOnProperty(value = "fiap.debug" , havingValue = "true")
              public class DebuggerController {

                /**
                 * Classe para ler properties
                 * @param properties
                 * @return
                 */

                private Environment env;

                /**
                 * Injecao de depedencia
                 * @param env
                 */
                public DebuggerController(Environment env) {
                  this.env = env;
                }

                @GetMapping
                String getProperty(@RequestParam("property") String properties) {
                  return env.getProperty(properties);
                }
                
                
# BeanConfiguration  
  - Instancia do objeto gerenciada do spring possibilita usar instancias de referencias de outras bibliotecas podendo editar e utilizar
  - Todo objeto que criamos via injecao de dependencia vai ser Singleton(Sempre vai existir apenas uma instancia desse objeto)
  
          @Service -> Trata regra de negocios (Indicativo que trabalhamos em regra de negocio) -> Sempre que vc estive uma service deve criar-se uma interface
          @Component -> realiza o mesmo procedimento do Service porem é generica
          @Repository -> Componente que vai realizar interacoes com dados , gravacao / leitura
          
# Audit
    Auditoria de quem criou ou editou uma entidade e quando essas alterações aconteceram.
  
# H2
    - Macos : jdbc:h2:~/fiapstockdatabase;DB_CLOSE_ON_EXIT=FALSE
    - Windows : jdbc:h2:file:/home/#userexemplo/fiapstockdatabase
    - H2 Console : http://localhost:8081/h2-console/
  
  
# Spring Security + JWT
     
     JWT), é um padrão que define uma forma segura de transmitir mensagens 
     utilizando um token compacto e self-contained no formato de um objeto JSON.
    
    - Camada de segurança
    - Passar um header com KEY : Authorization , Bearer {token}
    - Exemplo : Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmaWFwIiwiZXhwIjoxNjAwMjI3NjAxLCJpYXQiOjE2MDAyMjczMDF9.rpFFgxEkckMpdLmrcaPtwRzQv0-lZgLnF5hZei9ffT9A2wXrce_Z9GOImVbWfzebbf5rFRsfMbAQ_TpJqoqYAg
    
    
# Flyway

    Funcionalidade : Acompanhar versionamento da base de dados apartir de migrations e mantem a consistencia do banco de dados
 
# ApplicationContext

      ApplicationContext -> extensao do beanFeactory , Sempre Eager , cria e valida todas as instancias 
      quando a apliacacao sobe. 
      Instancia todas as classes ao inciiarlizar a apliacacao

# Swagger V3
 - http://localhost:8081/swagger-ui/index.html
 
 
