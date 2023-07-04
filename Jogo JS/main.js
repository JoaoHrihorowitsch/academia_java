// Variáveis globais
var mm = 0; // Minutos
var ss = 0; // Segundos
var ms = 0; // Milissegundos
var cron; // Variável do cronômetro
var pontuacao = 0; // Pontuação do jogador
var palavraGerada; // Elemento HTML que exibe a palavra gerada
var jogoBloqueado = true; // Controla se o jogo está bloqueado ou não
var nivel = 1; // Nível inicial do jogo
var tempoInicial = 3000; // Tempo inicial em milissegundos
var tempoLimite = tempoInicial / 1000; // Tempo limite inicial em segundos

// Evento de clique no botão "Iniciar"
document.querySelector('.iniciar').onclick = () => iniciarJogo();

// Função para iniciar o jogo
function iniciarJogo() {
  if (jogoBloqueado) {
    jogoBloqueado = false;

    // Atualiza o tempo limite com base no nível
    tempoLimite = tempoInicial - (nivel - 1) * 500; // Diminui 0,5 segundos por nível
    tempoLimite /= 1000; // Converte para segundos

    // Inicia o cronômetro
    cron = setInterval(() => {
      timer();
    }, 10);

    // Gera as cores aleatórias e a palavra
    generateRandomColors();
    gerarPalavra();
  }
}

// Função do cronômetro
function timer() {
  if ((ms += 10) === 1000) {
    ms = 0;
    ss++;
  }
  if (ss === 60) {
    ss = 0;
    mm++;
  }

  // Atualiza os elementos HTML com o tempo atual
  document.getElementById('minuto').innerText = mm;
  document.getElementById('segundo').innerText = ss;
  document.getElementById('milesegundo').innerText = ms;

  // Verifica se o tempo limite foi atingido
  if (ss >= tempoLimite) {
    clearInterval(cron);

    // Exibe a mensagem de tempo esgotado e pontuação
    var mensagemErro = document.querySelector('.mensagem-erro');
    var pontuacaoElement = document.getElementById('pontuacao');
    mensagemErro.innerHTML =
      'Tempo esgotado!<br><br>Sua pontuação foi: <span id="pontuacao">' + pontuacao + '</span>';
    mensagemErro.style.display = 'block';
    palavraGerada.style.display = 'none';
    jogoBloqueado = true;
  }
}

// Função para gerar as cores aleatórias
function generateRandomColors() {
  var cells = document.querySelectorAll('.tabela td');
  var colors = ['red', 'blue', 'green', 'yellow', 'orange', 'purple', 'pink', 'black', 'white'];

  cells.forEach(function (cell, index) {
    cell.style.backgroundColor = colors[index];
    cell.onclick = function () {
      if (!jogoBloqueado) {
        verificarSelecao(cell.style.backgroundColor);
      }
    };
  });
}

// Função para embaralhar as cores
function embaralhar() {
  if (jogoBloqueado) return;

  var cells = document.querySelectorAll('.tabela td');
  var colors = ['red', 'blue', 'green', 'yellow', 'orange', 'purple', 'pink', 'black', 'white'];

  // Algoritmo de embaralhamento Fisher-Yates
  for (var i = colors.length - 1; i > 0; i--) {
    var j = Math.floor(Math.random() * (i + 1));
    [colors[i], colors[j]] = [colors[j], colors[i]];
  }

  cells.forEach(function (cell, index) {
    cell.style.backgroundColor = colors[index];
    cell.onclick = function () {
      if (!jogoBloqueado) {
        verificarSelecao(cell.style.backgroundColor);
      }
    };
  });

  // Gera uma nova palavra
  gerarPalavra();
}

// Evento de clique na tabela para embaralhar as cores
var table = document.querySelector('.tabela');
table.addEventListener('click', embaralhar);

var cores = ['red', 'blue', 'green', 'yellow', 'orange', 'purple', 'pink', 'black', 'white'];
var palavras = ['Vermelho', 'Azul', 'Verde', 'Amarelo', 'Laranja', 'Roxo', 'Rosa', 'Preto', 'Branco'];

// Função para gerar uma palavra aleatória
function gerarPalavra() {
  var randomIndex = Math.floor(Math.random() * cores.length);
  var cor = cores[randomIndex];

  // Embaralha as palavras
  for (var i = palavras.length - 1; i > 0; i--) {
    var j = Math.floor(Math.random() * (i + 1));
    [palavras[i], palavras[j]] = [palavras[j], palavras[i]];
  }

  var palavra = palavras[randomIndex];

  // Atualiza o elemento HTML com a palavra gerada e a cor correspondente
  palavraGerada = document.querySelector('.palavra-gerada');
  palavraGerada.textContent = palavra;
  palavraGerada.style.color = cor;

  // Verifica se o jogador avançou para o próximo nível
  if (pontuacao >= nivel * 10) {
    nivel++;
    tempoLimite -= 0.5;
    if (tempoLimite < 0) tempoLimite = 0; // Define limite de tempo mínimo
    document.querySelector('.nivel h1').innerText = 'Nível ' + nivel;
  }
}

// Função para verificar a seleção do jogador
function verificarSelecao(corSelecionada) {
  var mensagemErro = document.querySelector('.mensagem-erro');
  var pontuacaoElement = document.getElementById('pontuacao');

  // Verifica se a cor selecionada está correta
  if (corSelecionada === palavraGerada.style.color) {
    pontuacao++;
    pontuacaoElement.innerText = pontuacao;
    embaralhar();
    gerarPalavra();
    reiniciarTempo();
  } else {
    clearInterval(cron);

    // Exibe a mensagem de erro e pontuação
    mensagemErro.innerHTML =
      'Que pena, você errou!<br><br>Sua pontuação foi: <span id="pontuacao">' + pontuacao + '</span>';
    mensagemErro.style.display = 'block';
    palavraGerada.style.display = 'none';
    jogoBloqueado = true;
  }
}

// Função para desabilitar o clique nas células da tabela
function desabilitarCliqueCelulas() {
  var cells = document.querySelectorAll('.tabela td');
  cells.forEach(function (cell) {
    cell.onclick = null;
  });
}

// Função para reiniciar o tempo
function reiniciarTempo() {
  mm = 0;
  ss = 0;
  ms = 0;

  // Atualiza os elementos HTML com o tempo reiniciado
  document.getElementById('minuto').innerText = mm;
  document.getElementById('segundo').innerText = ss;
  document.getElementById('milesegundo').innerText = ms;
}

// Função para resetar o jogo
function resetarJogo() {
  var container = document.querySelector('.container');
  if (container.classList.contains('jogo-bloqueado')) {
    container.classList.remove('jogo-bloqueado');
  }
  location.reload();
}

/*A função addEventListener é usada para adicionar um ouvinte de eventos a um elemento HTML. Ela permite que você especifique o tipo de evento a ser ouvido
(por exemplo, clique, carregamento da página, movimento do mouse) e uma função que será executada quando esse evento ocorrer no elemento.

O método addEventListener tem a seguinte sintaxe:
elemento.addEventListener(evento, função)

O parâmetro evento é uma string que especifica o tipo de evento a ser ouvido.
O parâmetro função é a função que será executada quando o evento ocorrer.
Por exemplo, o trecho de código abaixo adiciona um ouvinte de eventos de clique a um elemento com a classe .botao:

Nesse caso, quando o botão for clicado, a função passada como argumento será executada e exibirá a mensagem "O botão foi clicado!" no console.

O método addEventListener é útil para criar interações dinâmicas em uma página da web, pois permite que você responda a eventos específicos e execute ações correspondentes.*/


/*Você pode usar o querySelectorAll para buscar elementos com base em suas classes, IDs, tipos de elementos, hierarquia, atributos,
entre outros critérios de seleção definidos pelo CSS. A lista de elementos retornada pode ser posteriormente manipulada ou ter suas propriedades acessadas.*/

/* o forEach é uma forma prática e eficiente de percorrer e executar uma ação para cada elemento de um array, sem a necessidade de criar um loop manualmente.*/