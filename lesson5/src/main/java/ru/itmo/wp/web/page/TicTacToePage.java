package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static ru.itmo.wp.web.page.Phase.*;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    private static State state;

    private void action(Map<String, Object> view) {
        if (state == null) {
            state = new State();
        }
        view.put("state", state);
    }

    private void newGame(HttpServletResponse response, Map<String, Object> view) {
        state = null;
        try {
            response.sendRedirect("/ticTacToe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onMove(HttpServletRequest request, HttpServletResponse response, Map<String, Object> view) {
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            if (entry.getKey().startsWith("cell_")) {
                String key = entry.getKey();
                state.doMove(key.charAt(key.length() - 2) - '0', key.charAt(key.length() - 1) - '0');
                break;
            }
        }
        try {
            response.sendRedirect("/ticTacToe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class State {
        private int freeCells;
        private Phase phase;
        private final CellValue[][] cells;
        private boolean crossesMove;

        public State() {
            phase = RUNNING;
            cells = new CellValue[3][3];
            crossesMove = true;
            freeCells = 9;
        }


        private enum CellValue {
            X, O

        }

        void updatePhase() {
            if (isWinner(CellValue.X)) {
                phase = WON_X;
            } else if (isWinner(CellValue.O)) {
                phase = WON_O;
            } else if (!hasFreeCells()) {
                phase = DRAW;
            } else {
                phase = RUNNING;
            }
        }

        public boolean hasFreeCells() {
            return freeCells != 0;
        }


        public Phase getPhase() {
            return phase;
        }

        public int getSize() {
            return 3;
        }

        public CellValue[][] getCells() {
            return cells;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        private void doMove(int row, int col) {
            if (phase != RUNNING) {
                return;
            }
            if (notValidPtr(row) || notValidPtr(col)) {
                return;
            }
            if (cells[row][col] != null) {
                return;
            }
            cells[row][col] = isCrossesMove() ? CellValue.X : CellValue.O;
            crossesMove = !crossesMove;
            freeCells--;
            updatePhase();
        }

        private boolean notValidPtr(int ptr) {
            return 0 > ptr || ptr >= getSize();
        }

        boolean isWinner(CellValue toFind) {
            for (CellValue[] row : cells) {
                boolean contains = true;
                for (CellValue data : row) {
                    if (data != toFind) {
                        contains = false;
                        break;
                    }
                }
                if (contains) {
                    return true;
                }
            }
            for (int j = 0; j < 3; j++) {
                boolean contains = true;
                for (CellValue[] row : cells) {
                    if (row[j] != toFind) {
                        contains = false;
                        break;
                    }
                }
                if (contains) {
                    return true;
                }
            }
            boolean contains = true;
            for (int i = 0; i < 3; i++) {
                if (cells[i][i] != toFind) {
                    contains = false;
                    break;
                }
            }
            if (contains) {
                return true;
            } else {
                contains = true;
            }
            for (int i = 0; i < 3; i++) {
                if (cells[i][2 - i] != toFind) {
                    contains = false;
                    break;
                }
            }
            return contains;
        }
    }
}
