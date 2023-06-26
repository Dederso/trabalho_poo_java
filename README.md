# trabalho_poo_java
Trabalho de Programação Orientada a Objeto 

Autores:André Barros e Enzo Tristão

Código em java que simula uma planilha de ensino, le arquivos .csv especificos, contendo: cursos, disciplinas, alunos, avaliações e notas.
Depois gera um relatório em .csv com essas informações.

Para compilar o arquivo use o apache ant no mesmo diretório da pasta "src" e do arquivo build.xml
comandos:
ant compile == Compila o código e gera os arquivos .class.
ant run == Executa o programa com os arquivos padrão como especificação.
ant run-read-only == Executa o programa como read only, serializando os dados dos arquivos padrão em um arquivo chamado dados.dat.
ant run-write-only == Executa o programa como wirte only, lendo os dados serializados no arquivo dados.dat.
ant clean == Limpa todos os arquivos que não seja o arquivo "build.xml" e os arquivos que estejam na pasta "src".

Para que o programa funcione como esperado, é necessario que seja executado com estes arquivos no mesmo diretório que o build.xml:
cursos.csv, disciplinas.csv,  avaliacoes.csv, alunos.csv, notas.csv.
