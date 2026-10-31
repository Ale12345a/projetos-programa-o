// Criar um array com os números de 1 a 5
const numeros = [1, 2, 3, 4, 5];

// Utilizar reduce() para calcular a soma
const soma = numeros.reduce((acumulador, numero) => {
    return acumulador + numero;
}, 0);

// Apresentar o resultado
console.log("A soma dos números de 1 a 5 é:", soma);