function Login(){
    var nome = document.getElementById("nome").value.replace(/\s/g,"");
    var sobrenome = document.getElementById("sobrenome").value.replace(/\s/g,"");
    var login = (nome + "." + sobrenome).toLowerCase();
    document.getElementById("login").value = login;
}

function buscarCep(){
    var cep = document.getElementById("cep").value;
    const url = "https://viacep.com.br/ws/" + cep + "/json/";

    fetch(url)
    .then(response => response.json())
    .then(dados => {

        if(dados.erro){
            alert("Cep inválido")
        } else{
            document.getElementById("endereco").value = dados.logradouro;
            document.getElementById("bairro").value = dados.bairro;
            document.getElementById("cidade").value = dados.localidade;
            document.getElementById("estado").value = dados.uf;
        }

    })    
}

function rolarTexto(){
    var texto = document.getElementById("texto");
    var checkbox = document.getElementById("termos");

    if (texto.scrollHeight - texto.scrollTop === texto.clientHeight) {
        checkbox.disabled = false;
    }
}

function formularioSalvo(){
    var nome = document.getElementById("nome").value
    var sobrenome = document.getElementById("sobrenome").value
    var email = document.getElementById("email").value
    var login = document.getElementById("login").value
    var senha = document.getElementById("senha").value
    var cep = document.getElementById("cep").value
    var endereco = document.getElementById("endereco").value
    var complemento = document.getElementById("complemento").value
    var bairro = document.getElementById("bairro").value
    var cidade = document.getElementById("cidade").value
    var estado = document.getElementById("estado").value
    var github = document.getElementById("github").value
    var academia = document.getElementById("academia").value
    var professor = document.getElementById("professor").value

    var tabela = document.getElementById("tabela-dados")
    var nt = tabela.insertRow()

    nt.insertCell().textContent = nome
    nt.insertCell().textContent = sobrenome
    nt.insertCell().textContent = email
    nt.insertCell().textContent = login
    nt.insertCell().textContent = senha
    nt.insertCell().textContent = cep
    nt.insertCell().textContent = endereco
    nt.insertCell().textContent = complemento
    nt.insertCell().textContent = bairro
    nt.insertCell().textContent = cidade
    nt.insertCell().textContent = estado
    nt.insertCell().textContent = github
    nt.insertCell().textContent = academia
    nt.insertCell().textContent = professor

    alert("Cadastro salvo com sucesso")

    document.getElementById("nome").value = ""
    document.getElementById("sobrenome").value = ""
    document.getElementById("email").value = ""
    document.getElementById("login").value = ""
    document.getElementById("senha").value = ""
    document.getElementById("cep").value = ""
    document.getElementById("endereco").value = ""
    document.getElementById("complemento").value = ""
    document.getElementById("bairro").value = ""
    document.getElementById("cidade").value = ""
    document.getElementById("estado").value = ""
    document.getElementById("github").value = ""
    document.getElementById("academia").value = ""
    document.getElementById("professor").value = ""


}