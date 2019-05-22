package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.firuges.*;
import ru.job4j.chess.firuges.Cell;

import java.util.Arrays;



/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookBlack implements Figure {
    private final Cell position;
    private final String figureName;


    public RookBlack(final Cell position) {
        this.position = position;
        figureName = "Тура";
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /** Ходы туры
     * @param source задает исходную ячейку
     * @param dest задает ячейку, куда следует пойти
     * @return  Если фигура может туда пойти - возвращает массив ячеек пути
     * @throws ImpossibleMoveException
     */
    @Override
    public Cell[] way(Cell source, Cell dest)  throws ImpossibleMoveException {
        Cell[] steps = new Cell[7];
        int tmpX = source.x;
        int tmpY = source.y;
        int i = 0;
        if (LINE_TYPE.isLine(source, dest)) {
            int deltaX = DELTAS.getHorizontalDeltas(source, dest)[0];
            int deltaY = DELTAS.getHorizontalDeltas(source, dest)[1];
            int deltaMove = DELTAS.getHorizontalDeltas(source, dest)[2];

            for (i = 0; i < deltaMove; i++) {
                tmpX += deltaX;
                tmpY += deltaY;
                steps[i] = LOGIC.findCellByXY(tmpX, tmpY);
                //System.out.println(i + "  " + steps[i].x + "  " + steps[i].y);
            }
        } else {
            throw new ImpossibleMoveException("Тура так не ходит");
        }

        return Arrays.copyOf(steps, i);
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }

    @Override
    public void moveInfo(Cell source, Cell dest) {
        System.out.format("%s пошла %s - %s  \n", figureName, source, dest);
    }
}
