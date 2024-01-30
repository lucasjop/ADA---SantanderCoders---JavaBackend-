ADA - Santander Coders - Back End - Java
Lucas Cavalcanti Cruz

Projeto Final - Módulo 1: Agenda de Contatos:

Descrição:

App.java: Arquivo principal, com o método MAIN. Deve ser chamado para executar o programa.
Agenda.java: Classe agenda, gerencia funções relacionadas a uma agenda específica.
Contato.java: Classe com as funções e propriedades de cada contato. Foram utilizadas todas as propriedades descritas no documento de requisitos do projeto.
Telefone.java: Classe com as funções e propriedades de cada telefone. Foram utilizadas todas as propriedades descritas no documento de requisitos do projeto.

Tanto na classe Contato.java quanto Telefone.java foram necessários mais atributos e métedos dos que havia no documento de requisitos para poder implementar e rodar tudo conforme planejado.

Funcionamento do programa:

Ao iniciar o programa o é solicitado ao usuário a inserção de um nome. Esse nome vincula uma agenda a aquele usuário, bem como cria o arquivo .txt de mesmo nome. Ou seja cada usuário terá uma agenda diferente.
Assim o programa pode se usado por mais de uma pessoa sem misturar os contatos. Cada pessoa com a sua agenda.

Após inserir o nome o programa verifica se já existe uma agenda (uma arquivo .txt com aquele nome), se houver ele carrega as informações, se não houver uma nova agenda é criada.
A partir desse momento é só seguir com as funções do menu:
  1- Visualizar contatos: Lista todos os contatos cadastrados ou mensagem de não haver contatos.
  2 - Adicionar contatos: Ao escolher essa opção é solicitado do usuário a inserção de nome e sobrenome do contato. Se a combinação NOME+SOBRENOME já exisitir o programa
      acusa que aquele contato já exisite e pergunta se o usuário deseja editar. Se for um novo contato o programa segue solicitando um novo telefone (DDD e número). O número deve obedecer
      um formato específico (XXXX-XXXX ou XXXXX-XXXX). Após inserir o DDD e o número, o programa verifica se está no formato correto, se estiver verifica
      se aquele DDD+NÚMERO já pertence a algum contato. Caso pertença acusa o erro e mostra a quem pertence o número. Lembrando que apenas os contatos daquela agenda.
      Se não pertencer a ninguém, ou seja, se for um número novo, será cadastrado. Ao finalizar o cadastro do telefone o usuário pode inserir outros telefones, até dizer que não deseja mais.
  3 - Remover contato: O programa lista os contatos existentes com seus IDs e solicita que o usuário digite o ID de quem deseja remover. Após a escolha aparece uma mensagem de confirmação.
      Quando o usuário confirma, o contato é apagado.
  4 - Editar Contato: O programa lista todos os contatos. O usuário escolhe através do ID. Ao escolher aparece uma mensgaem de confirmação. Feita a escolha ele define o que deseja editar, o nome ou o telefone.
      Se escolher "nome", o programa solicita novos nome e sobrenome. Se escolher telefone o programa solicita que o usuário decida se deseja editar um número já salvo, adicionar um novo número para aquele contato
      ou excluir um número. Se o usuário escolher "Editar" ou "Remover" o programa exibe a lista com todos os números e seus IDs. O usuário escolhe qual telefone deseja editar ou remover. Se escolher remover
      aparece uma mensagem de confirmação. Após confirmar o telefone é removido. Se escolher "Editar" é solicitado que o usuário digite um novo DDD e um novo número. Sempre essa combinação de DDD+NÚMERO é verificada
      para saber se já existe em algum outro contato. Se o usuário escolher "Adicionar", um novo telefone será cadastrado.

A agenda não tem limitações quanto à quantidade de número cadastrados por contato nem a quantidade de contatos cadastrados.

Todo o processo é feito em memória. Ao escolher a opção "5 - Sair" todos as informações são salvas no arquivo .txt com uma string que representa cada contato e seus números.
A string é criada com um formato padrão, definido por mim. Assim na hora de carregar as informações eu consigo recuperar todos os dados necessários.

Bugs detectados: Ao salvar contatos que utilizam "acentos" (~ ou ^, etc...) houve um erro na hora de escrever no arquivo .txt. Consequentemente na hora de carregar os dados do .txt
esses dados são exibidos com erro. Será consertado na próxima atualização.

Qualquer dúvida sobre a utilização ou funcionalidades, entrem em contato através do e-mail: lucasjop@gmail.com

