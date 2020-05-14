import java.util.Queue;

public class Processo {
    private int burst, qtd_io, tempo_Q1, tempo_executado, tempo_io;
    private String nome;

    Processo (int burst, int io, String nome){
        this.burst = burst;
        this.qtd_io = io;
        this.nome = nome;
        this.tempo_Q1 = this.tempo_io = this.tempo_executado = 0;
    }

    public void imprimeprocesso (){ ///usado somente para teste
        System.out.println("Nome: " + nome + "quantidade de IO: " + qtd_io + "Tempo de Burst: " + burst + "Tempo: "
                            + tempo_Q1 + tempo_executado + tempo_io);
    }

    public void executa() {
        this.tempo_executado++;
    }


    public void toIO(Queue<Processo> rr, Queue<Processo> io) {
    }

    public void toFCFS(Queue<Processo> rr, Queue<Processo> fcfs) {
    }

    public void toRR(Queue<Processo> fcfs, Queue<Processo> rr) {
    }
}
