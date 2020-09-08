# Spring-MBA-FIAP


#BeanConfiguration

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
                
                
# Bean  
  - Instancia do objeto gerenciada do spring possibilita usar instancias de referencias de outras bibliotecas podendo editar e utilizar
  - Todo objeto que criamos via injecao de dependencia vai ser Singleton(Sempre vai existir apenas uma instancia desse objeto)
  
            @Service -> Trata regra de negocios (Indicativo que trabalhamos em regra de negocio) -> Sempre que vc estive uma service deve criar-se uma interface
          @Component -> realiza o mesmo procedimento do Service porem é generica
          @Repository -> Componente que vai realizar interacoes com dados , gravacao / leitura
