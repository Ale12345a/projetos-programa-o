class galo{
    constructor(id) {
        const parent = document.getElementById(id);

        // Criar tabuleiro
        const board = document.createElement('div');
        board.className = 'board';
        parent.appendChild(board);

        this.content = new Array(9).fill('');
        this.board = new Array(9);
        this.current = 'X';
        this.gameOver = false;

        // Criar células
        for (let i = 0; i < 9; i++) {
            const cell = document.createElement('div');
            cell.className = 'cell';
            board.appendChild(cell);
            cell.onclick = () => this.play(i);
            this.board[i] = cell;
        }

        // Mensagem de status
        this.status = document.createElement('div');
        this.status.id = 'status';
        this.status.innerText = `Próximo: ${this.current}`;
        parent.appendChild(this.status);

        // Botão reiniciar
        const restart = document.createElement('button');
        restart.innerText = "Reiniciar";
        restart.onclick = () => this.reset();
        parent.appendChild(restart);
    }

    play(pos) {
        if (this.content[pos] !== '' || this.gameOver) return;

        this.content[pos] = this.current;
        this.board[pos].innerHTML = this.current;

        if (this.checkWin(this.current)) {
            this.status.innerText = `Jogador ${this.current} ganhou!`;
            this.gameOver = true;
            return;
        }

        if (this.content.every(cell => cell !== '')) {
            this.status.innerText = "Empate!";
            this.gameOver = true;
            return;
        }

        // Alterna jogador
        this.current = (this.current === 'X' ? 'O' : 'X');
        this.status.innerText = `Próximo: ${this.current}`;
    }

    checkWin(player) {
        const winningCombos = [
            [0,1,2], [3,4,5], [6,7,8], // linhas
            [0,3,6], [1,4,7], [2,5,8], // colunas
            [0,4,8], [2,4,6]           // diagonais
        ];
        return winningCombos.some(combo =>
            combo.every(index => this.content[index] === player)
        );
    }

    reset() {
        this.content.fill('');
        this.board.forEach(cell => cell.innerHTML = '');
        this.current = 'X';
        this.status.innerText = `Próximo: ${this.current}`;
        this.gameOver = false;
    }
}

window.onload = function() {
    const game = new galo("base");
};
