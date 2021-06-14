# Mesa News - Clean Architecture

## Aplicativo que possibilita que usuários autenticados visualizem notícias em uma lista infinita, com separação dos destaques em um carrocel que fica no topo. Também é possível favoritar as notícias e visualizá-las em um webview. O usuário que não tiver cadastro poderá se cadastrar no app.

<img src="https://img.shields.io/github/v/release/augusstoflavio/mesa-news-clean-architecture?style=flat-square" />

### Arquitetura

O aplicativo foi desenvolvido aplicando a filosofia da arquitetura limpa, onde a aplicação é divida em camadas e o acesso é feito das camadas externas para as camadas internas e uma camada é desacoplada da outra com objetivo de gerar um código facilmente testável, reutilizável e com uma regra de negócio independente de tecnologia.

Estruturalmente os arquivos foram divididos em dois módulos, app e core, a principal diferença entre eles é que o módulo core é totalmente independente de tecnologia e de qualquer coisa que seja considerada um detalhe para a aplicação (web, banco de dados, ui ...), o módulo app conhece o core, o contrário não acontece:


- app
	- data
		... databases e api
	- di 
		... injeção de dependências
	- presentation
		... fragments, viewmodes, activitys
	
- core
	- data
		- dataSource
		- repository
	- domain
		- entity
		- repository
		- useCase

![Alt text](architecture.png)

### 🛠 Tecnologias utilizadas

- [koin](https://insert-koin.io/)
- [retrofit](https://square.github.io/retrofit/)
- [navigation](https://developer.android.com/guide/navigation)

### Telas

![image](https://user-images.githubusercontent.com/5657650/121913336-b03a2d80-cd07-11eb-9c31-af4e021bdafd.png)

![image](https://user-images.githubusercontent.com/5657650/121912795-402ba780-cd07-11eb-857b-df5742b85820.png)

![image](https://user-images.githubusercontent.com/5657650/121912846-4ae63c80-cd07-11eb-83d3-538c5db5140e.png)

![image](https://user-images.githubusercontent.com/5657650/121912968-62bdc080-cd07-11eb-9648-9835fb5775bd.png)

![image](https://user-images.githubusercontent.com/5657650/121913098-79fcae00-cd07-11eb-8083-86a188d1692c.png)






### Autor

<sub><b>Augusto Flávio</b></sub>

[![Linkedin Badge](https://img.shields.io/badge/-Augusto-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/augusto-flávio-mendonça-07477b60/)](https://www.linkedin.com/in/augusto-flávio-mendonça-07477b60/) 
[![Gmail Badge](https://img.shields.io/badge/augusto.flaviom@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:augusto.flaviom@gmail.com)](mailto:augusto.flaviom@gmail.com)
