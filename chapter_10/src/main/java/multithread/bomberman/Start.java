package multithread.bomberman;

/**
 * @author Ilya Ivannikov (voldores@mail.ru)
 * @version $Id$
 * @since 0.1
 */
/*
. Есть игровое поле Board. Представляющее из себя массив ReentrantLock[][] board.
Есть герои - Бомбермен и чудовища.
Есть две нити. Одна нить эмулирует поведение героя. То есть герой стоит на клетке board.
Клетка в этом случае должна быть заблокирована lock.lock();
Герой должен каждую секунду двигаться на новую клетку. При движении надо занять новую клетку,
то есть tryLock() - если не получилось в течении 500 мс., то изменить движение на другую клетку.
Избежать появление deadlock.
Поле board при движении героя не должно блокироваться целиком, блокируется только ячейка.
В коде использовать только immutable объекты, то есть все поля обозначить final.
В поле должны существовать блоки - поля куда нельзя ходить.
Бомбермен должен управляться через пользователя. (Пользовательский ввод реализовывать не надо) только апи, для движения.
Чудовища должны двигаться автоматически.
Предусмотреть, что если чудовище не может двинуться на клетку, например, там стоит другое чудовище,
проверять в течении 0.5 сек. и двигаться в другую строну.
Сложность поля и количество чудовищ должно варьироваться.
*/
public class Start {

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board(4);
        board.createBoard();
        Creature hero = new Creature(board, new Cell(0, 0), "hero", 1000);
        Creature monster1 = new Creature(board, new Cell(1, 2), "monster1", 1000);
        Creature monster2 = new Creature(board, new Cell(2, 3), "monster2", 1000);
        Thread threadHero = new Thread(hero, hero.getName());
        Thread mon1 = new Thread(monster1, monster1.getName());
        Thread mon2 = new Thread(monster2, monster2.getName());
        threadHero.start();
        mon1.start();
        mon2.start();
        Thread.sleep(10000);
        threadHero.interrupt();
        mon1.interrupt();
        mon2.interrupt();
    }
}
