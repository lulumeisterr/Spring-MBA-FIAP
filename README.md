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
