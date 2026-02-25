# 🛡️ Sistema Bancário: Tratamento de Exceções Personalizadas

Este projeto foi desenvolvido durante o módulo de **Tratamento de Exceções** do meu curso de Java. O foco aqui foi criar um sistema "blindado" contra erros de lógica de negócio e falhas de entrada do usuário.

## 📝 Sobre o Projeto
O sistema simula uma conta bancária onde é possível realizar saques. A grande diferença é que o programa não permite saques inválidos (valor maior que o saldo ou maior que o limite da conta). Em vez de usar vários `if/else` espalhados, o sistema utiliza **Exceções Customizadas**.

## 🎓 Conceitos de Java que pratiquei:

Neste exercício, foquei em tornar o código mais profissional e seguro:

* **Exceções Customizadas (`BusinessException`):** Criei minha própria classe de erro que herda de `RuntimeException`. Isso permite que o sistema lance mensagens de erro específicas para as regras do banco.
* **Bloco Try-Catch:** Implementei o tratamento de erros no programa principal. Se um erro ocorre no saque, o programa captura a exceção e mostra uma mensagem amigável em vez de "travar" e mostrar códigos estranhos na tela.
* **Programação Defensiva:** Criei um método chamado `validateWithdraw`. Ele verifica todos os possíveis erros **antes** de tentar realizar o saque. Isso é uma boa prática de segurança digital.
* **Cláusulas de Guarda:** O código foi escrito para interromper a execução assim que um erro é detectado, mantendo o método principal de saque (`withdraw`) limpo e fácil de ler.



## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java (JDK 17+)
* **Estrutura de Exceções:** `RuntimeException` para erros de tempo de execução.
* **Organização:** Pacotes separados para entidades (`model.entities`) e exceções (`model.exceptions`).

## 📁 Estrutura do Código
* `model.entities.Account`: Contém a lógica da conta e as validações de saque.
* `model.exceptions.BusinessException`: Classe personalizada para os erros de negócio.
* `application.Programa`: A interface que interage com o usuário e trata os possíveis erros.

## 🚀 Como este código se comporta:
- **Cenário A:** Se o usuário tenta sacar 600.00 tendo um limite de 500.00 -> O sistema lança a `BusinessException` com a mensagem: *"A quantia excede o limite de saque"*.
- **Cenário B:** Se o usuário digita uma letra no lugar do saldo -> O bloco `catch (RuntimeException)` captura o erro de entrada e avisa: *"Entrada de dados inválida"*.

---
⭐ **Este projeto demonstra que me preocupo com a experiência do usuário e com a segurança das regras de negócio do sistema!**
