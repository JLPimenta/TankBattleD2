package TankImplementation;

import TankSimulation.BlocoCenario;
import TankSimulation.Tank;
import TankSimulation.TankArena;
import javaengine.CSprite;
import javaengine.CTempo;

public class RocketTank extends Tank {

  CTempo tempo;
  int estado = 1;
  BlocoCenario ponto1;
  BlocoCenario ponto2;

  public RocketTank(CSprite[] sprite, TankArena arena) {
    super(sprite, arena);
    tempo = new CTempo(3000);
    tempo.inicia(gerenciadorTempo);
    ponto1 = matrizBlocos[1][0];
    ponto2 = matrizBlocos[9][7];
  }

  @Override
  public void executa() {
    rotacionaCanhao(retornaAnguloCanhao() + 1);
    if (!this.tankEmMovimento()) {
      if (estado == 1) {
        movePara(ponto2);
        estado = 2;
      } else {
        movePara(ponto1);
        estado = 1;
      }
    }

    if (this.retornaPosicaoAtual().linha == retornaBlocoTankInimigo().linha) {
      if (retornaPosicaoAtual().coluna < retornaBlocoTankInimigo().coluna) {
        rotacionaCanhao(0);
        atirar();
      }

      if (retornaPosicaoAtual().coluna > retornaBlocoTankInimigo().coluna) {
        rotacionaCanhao(180);
        atirar();
      }
    }

    if (this.retornaPosicaoAtual().coluna == retornaBlocoTankInimigo().coluna) {
      if (retornaPosicaoAtual().linha < retornaBlocoTankInimigo().linha) {
        rotacionaCanhao(90);
        atirar();
      }

      if (retornaPosicaoAtual().linha > retornaBlocoTankInimigo().linha) {
        rotacionaCanhao(-90);
        atirar();
      }
    }

    if (temPowerUp()) {
      if (retornaBlocoPowerUP() != null) {
        atualizaMovimento();

        movePara(retornaBlocoPowerUP());
      }
    }

    if (temEscudo()) {
      if (retornaBlocoPowerUP() != null) {
        atualizaPosicaoSprites();

        movePara(retornaBlocoPowerUP());
      }
    }
  }
}
