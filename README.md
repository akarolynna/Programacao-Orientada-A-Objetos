# Programacao-Orientada-A-Objetos
Repositório conterá o projeto de Programação Orientada a Objetos. **Todos os dados são fictícios e tem a finalidade de testar os conhecimentos aprendidos em aula!**

## Configurações iniciais
- [X] Criar o Banco de Dados;
- [X] Criar o datasource(-ds) e colocar ele na pasta deploy do Jboss;
- [X] Passar a pasta META-INF para dentro do meu scr;
- [X] Colocar dentro do pacote service o GenericService;
- [X] Configurar página inicial do projeto;


### Modelagem e Mapeamento das classes:
- [X] Modelagem da classe **Funcionário**;
- [X] Modelagem da classe **Filial**;
- [X] Modelagem da classe **Endereco**;


### Tela Inicial do projeto:
- [X] Crie uma página inicial;
- [X] Configure está página como a tela inicial do projeto;
- [X] Crie um link nela praa a tela **Gerenciar Filial**;
- [X] Crie um link nela praa a tela **Gerenciar Funcionário**;
- [X] Crie um link nela praa a tela **Gerenciar Relatório de Funcionários**;

### Tela Gerenciar Filial:
- [ ] Cadastrar filial
- [ ] Editar Filial;
- [ ] Listar Filial;
- [ ] Ordenar filiais pelo nome;
- [ ] Incluir todos os dados da Filial e **endereço** da mesma.
- [ ] Adicionar um campo que mostra o número de funcionários daquela filial;

### Tela Gerenciar Funcionários:
- [ ] Cadastrar Funcionário;
- [ ] Editar Funcionário;
- [ ] Listar Funcionário;
- [ ] Excluir Funcionário;
- [ ] Incluir todos os dados de Funcionários e seus respectivo **endereço**;
- [ ] Ordenar os funcionários pelo nome;
- [ ] Certifique-se de que a edição de funcionários não permita a troca de filial e exiba um alerta caso o usuário tente fazê-lo;

### Implementar filtro na tela de Gerenciar Funcionários:
- [ ] Adicionar a funcionalidade de listagem de funcionário pelo nome ou parte dele ***USE LIKE**;
- [ ] Caso campo fique em branco listar todos os funcionários;

### Tela de Relatórios:
- [ ] Filtre funcionários por filial;
- [ ] Filtre funcionários por faixa salarial;
- [ ] Implemente campos de seleção para filial;
- [ ] Implemente campos de input para a faixa salarial;
- [ ] Liste os funcionários por salário em **ORDEM DECRESCENTE**;
- [ ] Incluir os dados solicitados **NOME, SALARIO, CPF E NOME DA FILIAL**
- [ ] Considere também a opção de "Todas as Filiais" no filtro;

### Formatação e validação dos dados:
- [ ] Formatação adequada para moeda, CPF e CNPJ nas listagens;
- [ ] Todos os dados de Filial são obrigatórios;
- [ ] Todos os dados de Funcionário são obrigatórios;
- [ ] Caso fique em branco um dos campos deve-se emitir um alerta na tela;
- [ ] Implementar msgs de aviso caso as buscas e filtros não retornem nenhum valor;
