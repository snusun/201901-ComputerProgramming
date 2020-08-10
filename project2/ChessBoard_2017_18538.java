import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}

public class ChessBoard_2017_18538{
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JPanel chessBoard;
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Piece[][] chessBoardStatus = new Piece[8][8];
    private ImageIcon[] pieceImage_b = new ImageIcon[7];
    private ImageIcon[] pieceImage_w = new ImageIcon[7];
    private JLabel message = new JLabel("Enter Reset to Start");

    ChessBoard_2017_18538(){
        initPieceImages();
        initBoardStatus();
        initializeGui();
    }

    public final void initBoardStatus(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
        }
    }

    public final void initPieceImages(){
        pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

        pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    }

    public ImageIcon getImageIcon(Piece piece){
        if(piece.color.equals(PlayerColor.black)){
            if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
            else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
            else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
            else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
            else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
            else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
            else return pieceImage_b[6];
        }
        else if(piece.color.equals(PlayerColor.white)){
            if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
            else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
            else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
            else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
            else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
            else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
            else return pieceImage_w[6];
        }
        else return pieceImage_w[6];
    }

    public final void initializeGui(){
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        JButton startButton = new JButton("Reset");
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                initiateBoard();
            }
        });

        tools.add(startButton);
        tools.addSeparator();
        tools.add(message);

        chessBoard = new JPanel(new GridLayout(0, 8));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
        ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
        Insets buttonMargin = new Insets(0,0,0,0);
        for(int i=0; i<chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.addActionListener(new ButtonListener(i, j));
                b.setMargin(buttonMargin);
                b.setIcon(defaultIcon);
                if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
                else b.setBackground(Color.gray);
                b.setOpaque(true);
                b.setBorderPainted(false);
                chessBoardSquares[j][i] = b;
            }
        }

        for (int i=0; i < 8; i++) {
            for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);

        }
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ChessBoard_2017_18538 cb = new ChessBoard_2017_18538();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }

    //================================Utilize these functions========================================//

    class Piece{
        PlayerColor color;
        PieceType type;

        Piece(){
            color = PlayerColor.none;
            type = PieceType.none;
        }
        Piece(PlayerColor color, PieceType type){
            this.color = color;
            this.type = type;
        }
    }

    public void setIcon(int x, int y, Piece piece){
        chessBoardSquares[y][x].setIcon(getImageIcon(piece));
        chessBoardStatus[y][x] = piece;
    }

    public Piece getIcon(int x, int y){
        return chessBoardStatus[y][x];
    }

    public void markPosition(int x, int y){
        chessBoardSquares[y][x].setBackground(Color.pink);
    }

    public void unmarkPosition(int x, int y){
        if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
        else chessBoardSquares[y][x].setBackground(Color.gray);
    }

    public void setStatus(String inpt){
        message.setText(inpt);
    }

    public void initiateBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) setIcon(i, j, new Piece());
        }
        setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
        setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
        setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
        setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
        setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
        setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
        setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
        setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
        for(int i=0;i<8;i++){
            setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
            setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
        }
        setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
        setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
        setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
        setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
        setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
        setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
        setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
        setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) unmarkPosition(i, j);
        }
        onInitiateBoard();
    }
//======================================================Don't modify above==============================================================//




    //======================================================Implement below=================================================================//
    enum MagicType {MARK, CHECK, CHECKMATE};
    private int selX = -1, selY;
    private boolean check, checkmate, end;

    class ButtonListener implements ActionListener {
        int x;
        int y;

        ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
            //setStatus("BLACK\'s turn");
        }

        public void actionPerformed(ActionEvent e) {    // Only modify here
            // (x, y) is where the click event occurred
            Piece prePiece;
            Piece none = new Piece(PlayerColor.none, PieceType.none);
            Piece chessPiece = new Piece();
            Piece turnChecker = new Piece();
            boolean isBlackTurn = false;
            boolean move = false;
            boolean flag = false;
            String inpt = "";
            if(checkmate) return;

            /*if(isBlackTurn){
                inpt = check1(true);
                if(check) setStatus(inpt);
            }
            else{
                inpt = check1(false);
                if(check) setStatus(inpt);
            }*/

            if (selX == -1) {
                isBlackTurn = true;
                prePiece = none;
            } else if (selX == -2) {
                isBlackTurn = false;
                prePiece = none;
            } else prePiece = getIcon(selX, selY);

            if (prePiece.color.equals(PlayerColor.black)) isBlackTurn = true;
            else if (prePiece.color.equals(PlayerColor.white)) isBlackTurn = false;

            if (isBlackTurn) {
                if (!check) setStatus("BLACK\'s turn");
            } else {
                if (!check) setStatus("WHITE\'s turn");
            }

            if (isTherePink()) {
                if (chessBoardSquares[y][x].getBackground().equals(Color.pink)) {
                    setIcon(x, y, prePiece);
                    setIcon(selX, selY, none);
                    if (isBlackTurn) {
                        inpt = check1(true);
                        if (check) setStatus(inpt);
                        //if(checkmate) while(true);
                        else {
                            setStatus("WHITE\'s turn");
                            //check = false;
                        }
                        selX = -2;
                    } else {
                        inpt = check1(false);
                        if (check) setStatus(inpt);
                        //if(checkmate) while(true);
                        else {
                            setStatus("BLACK\'s turn");
                            //check = false;
                        }
                        selX = -1;
                    }
                    unmarkAll();
                    return;
                } else {
                    if (isBlackTurn) selX = -1;
                    else selX = -2;
                    unmarkAll();
                    return;
                }
            }

            chessPiece = getIcon(x, y);
            if (isBlackTurn) {
                if (chessPiece.color.equals(PlayerColor.black)) {
                    if (chessPiece.type.equals(PieceType.pawn)) {
                        moveBlackPawn(x, y);
                    } else if (chessPiece.type.equals(PieceType.rook)) {
                        moveBlackRook(x, y);
                    } else if (chessPiece.type.equals(PieceType.knight)) {
                        moveBlackKnight(x, y);
                    } else if (chessPiece.type.equals(PieceType.bishop)) {
                        moveBlackBishop(x, y);
                    } else if (chessPiece.type.equals(PieceType.queen)) {
                        moveBlackRook(x, y);
                        moveBlackBishop(x, y);
                    } else if (chessPiece.type.equals(PieceType.king)) {
                        moveBlackKing(x, y);
                    }

                    if (!isTherePink()) {
                        selX = -1;
                        return;
                    }
                } else return;
            } else {
                if (chessPiece.color.equals(PlayerColor.white)) {
                    if (chessPiece.type.equals(PieceType.pawn)) {
                        moveWhitePawn(x, y);
                    } else if (chessPiece.type.equals(PieceType.rook)) {
                        moveWhiteRook(x, y);
                    } else if (chessPiece.type.equals(PieceType.knight)) {
                        moveWhiteKnight(x, y);
                    } else if (chessPiece.type.equals(PieceType.bishop)) {
                        moveWhiteBishop(x, y);
                    } else if (chessPiece.type.equals(PieceType.queen)) {
                        moveWhiteRook(x, y);
                        moveWhiteBishop(x, y);
                    } else if (chessPiece.type.equals(PieceType.king)) {
                        moveWhiteKing(x, y);
                    }
                    if (!isTherePink()) {
                        selX = -2;
                        return;
                    }
                } else return;
            }
            selX = x;
            selY = y;

            //check 경로 색칠하되 return 있으면 안됨
            //inpt = check1(chessPiece);
            //if(check) setStatus(inpt);
            //checkmate 블랙턴에서 왕이 움직여도 체크 상태일때

            for (int i = 0; i < 8; i++)//end
            {
                for (int j = 0; j < 8; j++) {
                    Piece endChecker = new Piece();
                    endChecker = chessBoardStatus[j][i];
                    if (chessPiece.color.equals(PlayerColor.white)) {
                        if (endChecker.color.equals(PlayerColor.white) && endChecker.type.equals(PieceType.king))
                            return;
                        if ((i == 7 && j == 7)) {
                            unmarkAll();
                            checkmate = true;
                        }
                    } else if (chessPiece.color.equals(PlayerColor.black)) {
                        if (endChecker.color.equals(PlayerColor.black) && endChecker.type.equals(PieceType.king))
                            return;
                        if ((i == 7 && j == 7)) {
                            unmarkAll();
                            checkmate = true;
                        }
                    }
                }
            }
        }

        public void moveBlackPawn(int x, int y) {
            boolean movable = true;
            boolean attackable = true;
            Piece obstacle1 = new Piece();
            Piece obstacle2 = new Piece();
            Piece target1 = new Piece();
            Piece target2 = new Piece();

            if (x == 7) return;
            else if (x != 6 && y == 0) {
                obstacle1 = getIcon(x + 1, y);
                obstacle2 = getIcon(x + 2, y);
                target2 = getIcon(x + 1, y + 1);
                if (obstacle1.color.equals(PlayerColor.none)) {
                    if (x == 1)//x좌표가 1일 때
                    {
                        if (obstacle1.color.equals(PlayerColor.none) && obstacle2.color.equals(PlayerColor.none)) {
                            markPosition(x + 1, y);
                            markPosition(x + 2, y);
                        } else if (obstacle1.color.equals(PlayerColor.none) && !obstacle2.color.equals(PlayerColor.none))
                            markPosition(x + 1, y);
                    } else markPosition(x + 1, y);
                } else movable = false;

                if (target2.color.equals(PlayerColor.white)) markPosition(x + 1, y + 1);
                else attackable = false;

                if (!movable && !attackable) return;
            } else if (x != 6 && y == 7) {
                obstacle1 = getIcon(x + 1, y);
                obstacle2 = getIcon(x + 2, y);
                target1 = getIcon(x + 1, y - 1);
                if (obstacle1.color.equals(PlayerColor.none)) {
                    if (x == 1) {
                        if (obstacle1.color.equals(PlayerColor.none) && obstacle2.color.equals(PlayerColor.none)) {
                            markPosition(x + 1, y);
                            markPosition(x + 2, y);
                        } else if (obstacle1.color.equals(PlayerColor.none) && !obstacle2.color.equals(PlayerColor.none))
                            markPosition(x + 1, y);
                    } else markPosition(x + 1, y);
                } else movable = false;

                if (target1.color.equals(PlayerColor.white)) markPosition(x + 1, y - 1);
                else attackable = false;

                if (!movable && !attackable) return;
            } else if (x == 6) {
                if (y == 0) {
                    obstacle1 = getIcon(x + 1, y);
                    target2 = getIcon(x + 1, y + 1);
                    if (obstacle1.color.equals(PlayerColor.none)) markPosition(x + 1, y);
                    else movable = false;

                    if (target2.color.equals(PlayerColor.white)) markPosition(x + 1, y + 1);
                    else attackable = false;

                    if (!movable && !attackable) return;
                } else if (y == 7) {
                    obstacle1 = getIcon(x + 1, y);
                    target1 = getIcon(x + 1, y - 1);
                    if (obstacle1.color.equals(PlayerColor.none)) markPosition(x + 1, y);
                    else movable = false;

                    if (target1.color.equals(PlayerColor.white)) markPosition(x + 1, y - 1);
                    else attackable = false;

                    if (!movable && !attackable) return;
                } else {
                    obstacle1 = getIcon(x + 1, y);
                    target1 = getIcon(x + 1, y - 1);
                    target2 = getIcon(x + 1, y + 1);
                    if (obstacle1.color.equals(PlayerColor.none)) markPosition(x + 1, y);
                    else movable = false;

                    if (target1.color.equals(PlayerColor.white) || target2.color.equals(PlayerColor.white)) {
                        if (target1.color.equals(PlayerColor.white) && target2.color.equals(PlayerColor.white)) {
                            markPosition(x + 1, y - 1);
                            markPosition(x + 1, y + 1);
                        } else if (target1.color.equals(PlayerColor.white) && !target2.color.equals(PlayerColor.white))
                            markPosition(x + 1, y - 1);
                        else if (!target1.color.equals(PlayerColor.white) && target2.color.equals(PlayerColor.white))
                            markPosition(x + 1, y + 1);
                    } else attackable = false;

                    if (!movable && !attackable) return;
                }
            } else {
                obstacle1 = getIcon(x + 1, y);
                obstacle2 = getIcon(x + 2, y);
                target1 = getIcon(x + 1, y - 1);
                target2 = getIcon(x + 1, y + 1);
                if (obstacle1.color.equals(PlayerColor.none)) {
                    if (x == 1) {
                        if (obstacle1.color.equals(PlayerColor.none) && obstacle2.color.equals(PlayerColor.none)) {
                            markPosition(x + 1, y);
                            markPosition(x + 2, y);
                        } else if (obstacle1.color.equals(PlayerColor.none) && !obstacle2.color.equals(PlayerColor.none))
                            markPosition(x + 1, y);
                    } else markPosition(x + 1, y);
                } else movable = false;

                if (target1.color.equals(PlayerColor.white) || target2.color.equals(PlayerColor.white)) {
                    if (target1.color.equals(PlayerColor.white) && target2.color.equals(PlayerColor.white)) {
                        markPosition(x + 1, y - 1);
                        markPosition(x + 1, y + 1);
                    } else if (target1.color.equals(PlayerColor.white) && !target2.color.equals(PlayerColor.white))
                        markPosition(x + 1, y - 1);
                    else if (!target1.color.equals(PlayerColor.white) && target2.color.equals(PlayerColor.white))
                        markPosition(x + 1, y + 1);
                } else attackable = false;

                if (!movable && !attackable) return;
            }
        }

        public void moveBlackRook(int x, int y) {
            Piece obstacle1 = new Piece();//상
            Piece obstacle2 = new Piece();//하
            Piece obstacle3 = new Piece();//좌
            Piece obstacle4 = new Piece();//우
            int i = 1, j = 1, k = 1, l = 1;

            for (; x - i >= 0; i++) {
                obstacle1 = getIcon(x - i, y);
                if (!obstacle1.color.equals(PlayerColor.none)) break;
            }
            if (obstacle1.color.equals(PlayerColor.white)) {
                for (int num = 1; num < i + 1; num++) markPosition(x - num, y);
            } else if (obstacle1.color.equals(PlayerColor.black)) {
                for (int num = 1; num < i; num++) markPosition(x - num, y);
            } else {
                for (int num = 1; x - num >= 0; num++) markPosition(x - num, y);
            }

            for (; x + j < 8; j++) {
                obstacle2 = getIcon(x + j, y);
                if (!obstacle2.color.equals(PlayerColor.none)) break;
            }
            if (obstacle2.color.equals(PlayerColor.white)) {
                for (int num = 1; num < j + 1; num++) markPosition(x + num, y);
            } else if (obstacle2.color.equals(PlayerColor.black)) {
                for (int num = 1; num < j; num++) markPosition(x + num, y);
            } else {
                for (int num = 1; x + num < 8; num++) markPosition(x + num, y);
            }

            for (; y - k >= 0; k++) {
                obstacle3 = getIcon(x, y - k);
                if (!obstacle3.color.equals(PlayerColor.none)) break;
            }
            if (obstacle3.color.equals(PlayerColor.white)) {
                for (int num = 1; num < k + 1; num++) markPosition(x, y - num);
            } else if (obstacle3.color.equals(PlayerColor.black)) {
                for (int num = 1; num < k; num++) markPosition(x, y - num);
            } else {
                for (int num = 1; y - num >= 0; num++) markPosition(x, y - num);
            }

            for (; y + l < 8; l++) {
                obstacle4 = getIcon(x, y + l);
                if (!obstacle4.color.equals(PlayerColor.none)) break;
            }
            if (obstacle4.color.equals(PlayerColor.white)) {
                for (int num = 1; num < l + 1; num++) markPosition(x, y + num);
            } else if (obstacle4.color.equals(PlayerColor.black)) {
                for (int num = 1; num < l; num++) markPosition(x, y + num);
            } else {
                for (int num = 1; y + num < 8; num++) markPosition(x, y + num);
            }
        }

        public void moveBlackKnight(int x, int y) {
            Piece obstacle1 = new Piece();
            Piece obstacle2 = new Piece();
            Piece obstacle3 = new Piece();
            Piece obstacle4 = new Piece();
            Piece obstacle5 = new Piece();
            Piece obstacle6 = new Piece();
            Piece obstacle7 = new Piece();
            Piece obstacle8 = new Piece();

            if (x - 2 >= 0 && y + 1 < 8) {
                obstacle1 = getIcon(x - 2, y + 1);
                if (!obstacle1.color.equals(PlayerColor.black)) markPosition(x - 2, y + 1);
            }
            if (x - 1 >= 0 && y + 2 < 8) {
                obstacle2 = getIcon(x - 1, y + 2);
                if (!obstacle2.color.equals(PlayerColor.black)) markPosition(x - 1, y + 2);
            }
            if (x + 1 < 8 && y + 2 < 8) {
                obstacle3 = getIcon(x + 1, y + 2);
                if (!obstacle3.color.equals(PlayerColor.black)) markPosition(x + 1, y + 2);
            }
            if (x + 2 < 8 && y + 1 < 8) {
                obstacle4 = getIcon(x + 2, y + 1);
                if (!obstacle4.color.equals(PlayerColor.black)) markPosition(x + 2, y + 1);
            }
            if (x + 2 < 8 && y - 1 >= 0) {
                obstacle5 = getIcon(x + 2, y - 1);
                if (!obstacle5.color.equals(PlayerColor.black)) markPosition(x + 2, y - 1);
            }
            if (x + 1 < 8 && y - 2 >= 0) {
                obstacle6 = getIcon(x + 1, y - 2);
                if (!obstacle6.color.equals(PlayerColor.black)) markPosition(x + 1, y - 2);
            }
            if (x - 1 >= 0 && y - 2 >= 0) {
                obstacle7 = getIcon(x - 1, y - 2);
                if (!obstacle7.color.equals(PlayerColor.black)) markPosition(x - 1, y - 2);
            }
            if (x - 2 >= 0 && y - 1 >= 0) {
                obstacle8 = getIcon(x - 2, y - 1);
                if (!obstacle8.color.equals(PlayerColor.black)) markPosition(x - 2, y - 1);
            }
        }

        public void moveBlackBishop(int x, int y) {
            Piece obstacle1 = new Piece();//왼쪽 위
            Piece obstacle2 = new Piece();//오른쪽 위
            Piece obstacle3 = new Piece();//오른쪽 아래
            Piece obstacle4 = new Piece();//왼쪽 아래
            int i = 1, j = 1, k = 1, l = 1;

            for (; x - i >= 0 && y - i >= 0; i++) {
                obstacle1 = getIcon(x - i, y - i);
                if (!obstacle1.color.equals(PlayerColor.none)) break;
            }
            if (obstacle1.color.equals(PlayerColor.white)) {
                for (int num = 1; num < i + 1; num++) markPosition(x - num, y - num);
            } else if (obstacle1.color.equals(PlayerColor.black)) {
                for (int num = 1; num < i; num++) markPosition(x - num, y - num);
            } else {
                for (int num = 1; x - num >= 0 && y - num >= 0; num++) markPosition(x - num, y - num);
            }

            for (; x - j >= 0 && y + j < 8; j++) {
                obstacle2 = getIcon(x - j, y + j);
                if (!obstacle2.color.equals(PlayerColor.none)) break;
            }
            if (obstacle2.color.equals(PlayerColor.white)) {
                for (int num = 1; num < j + 1; num++) markPosition(x - num, y + num);
            } else if (obstacle2.color.equals(PlayerColor.black)) {
                for (int num = 1; num < j; num++) markPosition(x - num, y + num);
            } else {
                for (int num = 1; x - num >= 0 && y + num < 8; num++) markPosition(x - num, y + num);
            }

            for (; x + k < 8 && y + k < 8; k++) {
                obstacle3 = getIcon(x + k, y + k);
                if (!obstacle3.color.equals(PlayerColor.none)) break;
            }
            if (obstacle3.color.equals(PlayerColor.white)) {
                for (int num = 1; num < k + 1; num++) markPosition(x + num, y + num);
            } else if (obstacle3.color.equals(PlayerColor.black)) {
                for (int num = 1; num < k; num++) markPosition(x + num, y + num);
            } else {
                for (int num = 1; x + num < 8 && y + num < 8; num++) markPosition(x + num, y + num);
            }

            for (; x + l < 8 && y - l >= 0; l++) {
                obstacle4 = getIcon(x + l, y - l);
                if (!obstacle4.color.equals(PlayerColor.none)) break;
            }
            if (obstacle4.color.equals(PlayerColor.white)) {
                for (int num = 1; num < l + 1; num++) markPosition(x + num, y - num);
            } else if (obstacle4.color.equals(PlayerColor.black)) {
                for (int num = 1; num < l; num++) markPosition(x + num, y - num);
            } else {
                for (int num = 1; x + num < 8 && y - num >= 0; num++) markPosition(x + num, y - num);
            }
        }

        public void moveBlackKing(int x, int y) {
            Piece obstacle1 = new Piece(); //왼쪽 상
            Piece obstacle2 = new Piece(); //상
            Piece obstacle3 = new Piece(); //오른쪽 상
            Piece obstacle4 = new Piece(); //우
            Piece obstacle5 = new Piece(); //오른쪽 아래
            Piece obstacle6 = new Piece(); //하
            Piece obstacle7 = new Piece(); //왼쪽 아래
            Piece obstacle8 = new Piece(); //좌

            if (x == 0 && y == 0) {
                obstacle4 = getIcon(x, y + 1);
                obstacle5 = getIcon(x + 1, y + 1);
                obstacle6 = getIcon(x + 1, y);
                if (!obstacle4.color.equals(PlayerColor.black)) markPosition(x, y + 1);
                if (!obstacle5.color.equals(PlayerColor.black)) markPosition(x + 1, y + 1);
                if (!obstacle6.color.equals(PlayerColor.black)) markPosition(x + 1, y);
            } else if (x == 0 && y == 7) {
                obstacle6 = getIcon(x + 1, y);
                obstacle7 = getIcon(x + 1, y - 1);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle6.color.equals(PlayerColor.black)) markPosition(x + 1, y);
                if (!obstacle7.color.equals(PlayerColor.black)) markPosition(x + 1, y - 1);
                if (!obstacle8.color.equals(PlayerColor.black)) markPosition(x, y - 1);
            } else if (x == 7 && y == 0) {
                obstacle2 = getIcon(x - 1, y);
                obstacle3 = getIcon(x - 1, y + 1);
                obstacle4 = getIcon(x, y + 1);
                if (!obstacle2.color.equals(PlayerColor.black)) markPosition(x - 1, y);
                if (!obstacle3.color.equals(PlayerColor.black)) markPosition(x - 1, y + 1);
                if (!obstacle4.color.equals(PlayerColor.black)) markPosition(x, y + 1);
            } else if (x == 7 && y == 7) {
                obstacle1 = getIcon(x - 1, y - 1);
                obstacle2 = getIcon(x - 1, y);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle1.color.equals(PlayerColor.black)) markPosition(x - 1, y - 1);
                if (!obstacle2.color.equals(PlayerColor.black)) markPosition(x - 1, y);
                if (!obstacle8.color.equals(PlayerColor.black)) markPosition(x, y - 1);
            } else if (x == 0 && !(y == 0) && !(y == 7)) {
                obstacle4 = getIcon(x, y + 1);
                obstacle5 = getIcon(x + 1, y + 1);
                obstacle6 = getIcon(x + 1, y);
                obstacle7 = getIcon(x + 1, y - 1);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle4.color.equals(PlayerColor.black)) markPosition(x, y + 1);
                if (!obstacle5.color.equals(PlayerColor.black)) markPosition(x + 1, y + 1);
                if (!obstacle6.color.equals(PlayerColor.black)) markPosition(x + 1, y);
                if (!obstacle7.color.equals(PlayerColor.black)) markPosition(x + 1, y - 1);
                if (!obstacle8.color.equals(PlayerColor.black)) markPosition(x, y - 1);
            } else if (x == 7 && !(y == 0) && !(y == 7)) {
                obstacle1 = getIcon(x - 1, y - 1);
                obstacle2 = getIcon(x - 1, y);
                obstacle3 = getIcon(x - 1, y + 1);
                obstacle4 = getIcon(x, y + 1);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle1.color.equals(PlayerColor.black)) markPosition(x - 1, y - 1);
                if (!obstacle2.color.equals(PlayerColor.black)) markPosition(x - 1, y);
                if (!obstacle3.color.equals(PlayerColor.black)) markPosition(x - 1, y + 1);
                if (!obstacle4.color.equals(PlayerColor.black)) markPosition(x, y + 1);
                if (!obstacle8.color.equals(PlayerColor.black)) markPosition(x, y - 1);
            } else if (y == 0 && !(x == 0) && !(x == 7)) {
                obstacle2 = getIcon(x - 1, y);
                obstacle3 = getIcon(x - 1, y + 1);
                obstacle4 = getIcon(x, y + 1);
                obstacle5 = getIcon(x + 1, y + 1);
                obstacle6 = getIcon(x + 1, y);
                if (!obstacle2.color.equals(PlayerColor.black)) markPosition(x - 1, y);
                if (!obstacle3.color.equals(PlayerColor.black)) markPosition(x - 1, y + 1);
                if (!obstacle4.color.equals(PlayerColor.black)) markPosition(x, y + 1);
                if (!obstacle5.color.equals(PlayerColor.black)) markPosition(x + 1, y + 1);
                if (!obstacle6.color.equals(PlayerColor.black)) markPosition(x + 1, y);
            } else if (y == 7 && !(x == 0) && !(x == 7)) {
                obstacle1 = getIcon(x - 1, y - 1);
                obstacle2 = getIcon(x - 1, y);
                obstacle6 = getIcon(x + 1, y);
                obstacle7 = getIcon(x + 1, y - 1);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle1.color.equals(PlayerColor.black)) markPosition(x - 1, y - 1);
                if (!obstacle2.color.equals(PlayerColor.black)) markPosition(x - 1, y);
                if (!obstacle6.color.equals(PlayerColor.black)) markPosition(x + 1, y);
                if (!obstacle7.color.equals(PlayerColor.black)) markPosition(x + 1, y - 1);
                if (!obstacle8.color.equals(PlayerColor.black)) markPosition(x, y - 1);
            } else {
                obstacle1 = getIcon(x - 1, y - 1);
                obstacle2 = getIcon(x - 1, y);
                obstacle3 = getIcon(x - 1, y + 1);
                obstacle4 = getIcon(x, y + 1);
                obstacle5 = getIcon(x + 1, y + 1);
                obstacle6 = getIcon(x + 1, y);
                obstacle7 = getIcon(x + 1, y - 1);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle1.color.equals(PlayerColor.black)) markPosition(x - 1, y - 1);
                if (!obstacle2.color.equals(PlayerColor.black)) markPosition(x - 1, y);
                if (!obstacle3.color.equals(PlayerColor.black)) markPosition(x - 1, y + 1);
                if (!obstacle4.color.equals(PlayerColor.black)) markPosition(x, y + 1);
                if (!obstacle5.color.equals(PlayerColor.black)) markPosition(x + 1, y + 1);
                if (!obstacle6.color.equals(PlayerColor.black)) markPosition(x + 1, y);
                if (!obstacle7.color.equals(PlayerColor.black)) markPosition(x + 1, y - 1);
                if (!obstacle8.color.equals(PlayerColor.black)) markPosition(x, y - 1);
            }
        }

        public void moveWhitePawn(int x, int y) {
            boolean movable = true;
            boolean attackable = true;
            Piece obstacle1 = new Piece();
            Piece obstacle2 = new Piece();
            Piece target1 = new Piece();
            Piece target2 = new Piece();

            if (x == 0) return;
            else if (x != 1 && y == 0) {
                obstacle1 = getIcon(x - 1, y);
                obstacle2 = getIcon(x - 2, y);
                target2 = getIcon(x - 1, y + 1);
                if (obstacle1.color.equals(PlayerColor.none)) {
                    if (x == 6) {
                        if (obstacle2.color.equals(PlayerColor.none)) {
                            markPosition(x - 1, y);
                            markPosition(x - 2, y);
                        } else markPosition(x - 1, y);
                    } else markPosition(x - 1, y);
                } else movable = false;

                if (target2.color.equals(PlayerColor.black)) markPosition(x - 1, y + 1);
                else attackable = false;

                if (!movable && !attackable) return;
            } else if (x != 1 && y == 7) {
                obstacle1 = getIcon(x - 1, y);
                obstacle2 = getIcon(x - 2, y);
                target1 = getIcon(x - 1, y - 1);
                if (obstacle1.color.equals(PlayerColor.none)) {
                    if (x == 6) {
                        if (obstacle2.color.equals(PlayerColor.none)) {
                            markPosition(x - 1, y);
                            markPosition(x - 2, y);
                        } else markPosition(x - 1, y);
                    } else markPosition(x - 1, y);
                } else movable = false;

                if (target1.color.equals(PlayerColor.black)) markPosition(x - 1, y - 1);
                else attackable = false;

                if (!movable && !attackable) return;
            } else if (x == 1) {
                if (y == 0) {
                    obstacle1 = getIcon(x - 1, y);
                    target2 = getIcon(x - 1, y + 1);
                    if (obstacle1.color.equals(PlayerColor.none)) markPosition(x - 1, y);
                    else movable = false;

                    if (target2.color.equals(PlayerColor.black)) markPosition(x - 1, y + 1);
                    else attackable = false;

                    if (!movable && !attackable) return;
                } else if (y == 7) {
                    obstacle1 = getIcon(x - 1, y);
                    target1 = getIcon(x - 1, y - 1);
                    if (obstacle1.color.equals(PlayerColor.none)) markPosition(x - 1, y);
                    else movable = false;

                    if (target1.color.equals(PlayerColor.black)) markPosition(x - 1, y - 1);
                    else attackable = false;

                    if (!movable && !attackable) return;
                } else {
                    obstacle1 = getIcon(x - 1, y);
                    target1 = getIcon(x - 1, y - 1);
                    target2 = getIcon(x - 1, y + 1);
                    if (obstacle1.color.equals(PlayerColor.none)) markPosition(x - 1, y);
                    else movable = false;

                    if (target1.color.equals(PlayerColor.black) || target2.color.equals(PlayerColor.black)) {
                        if (target1.color.equals(PlayerColor.black) && target2.color.equals(PlayerColor.black)) {
                            markPosition(x - 1, y - 1);
                            markPosition(x - 1, y + 1);
                        } else if (target1.color.equals(PlayerColor.black) && !target2.color.equals(PlayerColor.black))
                            markPosition(x - 1, y - 1);
                        else if (!target1.color.equals(PlayerColor.black) && target2.color.equals(PlayerColor.black))
                            markPosition(x - 1, y + 1);
                    } else attackable = false;

                    if (!movable && !attackable) return;
                }
            } else {
                obstacle1 = getIcon(x - 1, y);
                obstacle2 = getIcon(x - 2, y);
                target1 = getIcon(x - 1, y - 1);
                target2 = getIcon(x - 1, y + 1);
                if (obstacle1.color.equals(PlayerColor.none)) {
                    if (x == 6) {
                        if (obstacle2.color.equals(PlayerColor.none)) {
                            markPosition(x - 1, y);
                            markPosition(x - 2, y);
                        } else markPosition(x - 1, y);
                    } else markPosition(x - 1, y);
                } else movable = false;

                if (target1.color.equals(PlayerColor.black) || target2.color.equals(PlayerColor.black)) {
                    if (target1.color.equals(PlayerColor.black) && target2.color.equals(PlayerColor.black)) {
                        markPosition(x - 1, y - 1);
                        markPosition(x - 1, y + 1);
                    } else if (target1.color.equals(PlayerColor.black) && !target2.color.equals(PlayerColor.black))
                        markPosition(x - 1, y - 1);
                    else if (!target1.color.equals(PlayerColor.black) && target2.color.equals(PlayerColor.black))
                        markPosition(x - 1, y + 1);
                } else attackable = false;

                if (!movable && !attackable) return;
            }
        }

        public void moveWhiteRook(int x, int y) {
            Piece obstacle1 = new Piece();//상
            Piece obstacle2 = new Piece();//하
            Piece obstacle3 = new Piece();//좌
            Piece obstacle4 = new Piece();//우
            int i = 1, j = 1, k = 1, l = 1;

            for (; x - i >= 0; i++) {
                obstacle1 = getIcon(x - i, y);
                if (!obstacle1.color.equals(PlayerColor.none)) break;
            }
            if (obstacle1.color.equals(PlayerColor.black)) {
                for (int num = 1; num < i + 1; num++) markPosition(x - num, y);
            } else if (obstacle1.color.equals(PlayerColor.white)) {
                for (int num = 1; num < i; num++) markPosition(x - num, y);
            } else {
                for (int num = 1; x - num >= 0; num++) markPosition(x - num, y);
            }

            for (; x + j < 8; j++) {
                obstacle2 = getIcon(x + j, y);
                if (!obstacle2.color.equals(PlayerColor.none)) break;
            }
            if (obstacle2.color.equals(PlayerColor.black)) {
                for (int num = 1; num < j + 1; num++) markPosition(x + num, y);
            } else if (obstacle2.color.equals(PlayerColor.white)) {
                for (int num = 1; num < j; num++) markPosition(x + num, y);
            } else {
                for (int num = 1; x + num < 8; num++) markPosition(x + num, y);
            }

            for (; y - k >= 0; k++) {
                obstacle3 = getIcon(x, y - k);
                if (!obstacle3.color.equals(PlayerColor.none)) break;
            }
            if (obstacle3.color.equals(PlayerColor.black)) {
                for (int num = 1; num < k + 1; num++) markPosition(x, y - num);
            } else if (obstacle3.color.equals(PlayerColor.white)) {
                for (int num = 1; num < k; num++) markPosition(x, y - num);
            } else {
                for (int num = 1; y - num >= 0; num++) markPosition(x, y - num);
            }

            for (; y + l < 8; l++) {
                obstacle4 = getIcon(x, y + l);
                if (!obstacle4.color.equals(PlayerColor.none)) break;
            }
            if (obstacle4.color.equals(PlayerColor.black)) {
                for (int num = 1; num < l + 1; num++) markPosition(x, y + num);
            } else if (obstacle4.color.equals(PlayerColor.white)) {
                for (int num = 1; num < l; num++) markPosition(x, y + num);
            } else {
                for (int num = 1; y + num < 8; num++) markPosition(x, y + num);
            }
        }

        public void moveWhiteKnight(int x, int y) {
            Piece obstacle1 = new Piece();
            Piece obstacle2 = new Piece();
            Piece obstacle3 = new Piece();
            Piece obstacle4 = new Piece();
            Piece obstacle5 = new Piece();
            Piece obstacle6 = new Piece();
            Piece obstacle7 = new Piece();
            Piece obstacle8 = new Piece();

            if (x - 2 >= 0 && y + 1 < 8) {
                obstacle1 = getIcon(x - 2, y + 1);
                if (!obstacle1.color.equals(PlayerColor.white)) markPosition(x - 2, y + 1);
            }
            if (x - 1 >= 0 && y + 2 < 8) {
                obstacle2 = getIcon(x - 1, y + 2);
                if (!obstacle2.color.equals(PlayerColor.white)) markPosition(x - 1, y + 2);
            }
            if (x + 1 < 8 && y + 2 < 8) {
                obstacle3 = getIcon(x + 1, y + 2);
                if (!obstacle3.color.equals(PlayerColor.white)) markPosition(x + 1, y + 2);
            }
            if (x + 2 < 8 && y + 1 < 8) {
                obstacle4 = getIcon(x + 2, y + 1);
                if (!obstacle4.color.equals(PlayerColor.white)) markPosition(x + 2, y + 1);
            }
            if (x + 2 < 8 && y - 1 >= 0) {
                obstacle5 = getIcon(x + 2, y - 1);
                if (!obstacle5.color.equals(PlayerColor.white)) markPosition(x + 2, y - 1);
            }
            if (x + 1 < 8 && y - 2 >= 0) {
                obstacle6 = getIcon(x + 1, y - 2);
                if (!obstacle6.color.equals(PlayerColor.white)) markPosition(x + 1, y - 2);
            }
            if (x - 1 >= 0 && y - 2 >= 0) {
                obstacle7 = getIcon(x - 1, y - 2);
                if (!obstacle7.color.equals(PlayerColor.white)) markPosition(x - 1, y - 2);
            }
            if (x - 2 >= 0 && y - 1 >= 0) {
                obstacle8 = getIcon(x - 2, y - 1);
                if (!obstacle8.color.equals(PlayerColor.white)) markPosition(x - 2, y - 1);
            }
        }

        public void moveWhiteBishop(int x, int y) {
            Piece obstacle1 = new Piece();//왼쪽 위
            Piece obstacle2 = new Piece();//오른쪽 위
            Piece obstacle3 = new Piece();//오른쪽 아래
            Piece obstacle4 = new Piece();//왼쪽 아래
            int i = 1, j = 1, k = 1, l = 1;

            for (; x - i >= 0 && y - i >= 0; i++) {
                obstacle1 = getIcon(x - i, y - i);
                if (!obstacle1.color.equals(PlayerColor.none)) break;
            }
            if (obstacle1.color.equals(PlayerColor.black)) {
                for (int num = 1; num < i + 1; num++) markPosition(x - num, y - num);
            } else if (obstacle1.color.equals(PlayerColor.white)) {
                for (int num = 1; num < i; num++) markPosition(x - num, y - num);
            } else {
                for (int num = 1; x - num >= 0 && y - num >= 0; num++) markPosition(x - num, y - num);
            }

            for (; x - j >= 0 && y + j < 8; j++) {
                obstacle2 = getIcon(x - j, y + j);
                if (!obstacle2.color.equals(PlayerColor.none)) break;
            }
            if (obstacle2.color.equals(PlayerColor.black)) {
                for (int num = 1; num < j + 1; num++) markPosition(x - num, y + num);
            } else if (obstacle2.color.equals(PlayerColor.white)) {
                for (int num = 1; num < j; num++) markPosition(x - num, y + num);
            } else {
                for (int num = 1; x - num >= 0 && y + num < 8; num++) markPosition(x - num, y + num);
            }

            for (; x + k < 8 && y + k < 8; k++) {
                obstacle3 = getIcon(x + k, y + k);
                if (!obstacle3.color.equals(PlayerColor.none)) break;
            }
            if (obstacle3.color.equals(PlayerColor.black)) {
                for (int num = 1; num < k + 1; num++) markPosition(x + num, y + num);
            } else if (obstacle3.color.equals(PlayerColor.white)) {
                for (int num = 1; num < k; num++) markPosition(x + num, y + num);
            } else {
                for (int num = 1; x + num < 8 && y + num < 8; num++) markPosition(x + num, y + num);
            }

            for (; x + l < 8 && y - l >= 0; l++) {
                obstacle4 = getIcon(x + l, y - l);
                if (!obstacle4.color.equals(PlayerColor.none)) break;
            }
            if (obstacle4.color.equals(PlayerColor.black)) {
                for (int num = 1; num < l + 1; num++) markPosition(x + num, y - num);
            } else if (obstacle4.color.equals(PlayerColor.white)) {
                for (int num = 1; num < l; num++) markPosition(x + num, y - num);
            } else {
                for (int num = 1; x + num < 8 && y - num >= 0; num++) markPosition(x + num, y - num);
            }
        }

        public void moveWhiteKing(int x, int y) {
            Piece obstacle1 = new Piece(); //왼쪽 상
            Piece obstacle2 = new Piece(); //상
            Piece obstacle3 = new Piece(); //오른쪽 상
            Piece obstacle4 = new Piece(); //우
            Piece obstacle5 = new Piece(); //오른쪽 아래
            Piece obstacle6 = new Piece(); //하
            Piece obstacle7 = new Piece(); //왼쪽 아래
            Piece obstacle8 = new Piece(); //좌

            if (x == 0 && y == 0) {
                obstacle4 = getIcon(x, y + 1);
                obstacle5 = getIcon(x + 1, y + 1);
                obstacle6 = getIcon(x + 1, y);
                if (!obstacle4.color.equals(PlayerColor.white)) markPosition(x, y + 1);
                if (!obstacle5.color.equals(PlayerColor.white)) markPosition(x + 1, y + 1);
                if (!obstacle6.color.equals(PlayerColor.white)) markPosition(x + 1, y);
            } else if (x == 0 && y == 7) {
                obstacle6 = getIcon(x + 1, y);
                obstacle7 = getIcon(x + 1, y - 1);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle6.color.equals(PlayerColor.white)) markPosition(x + 1, y);
                if (!obstacle7.color.equals(PlayerColor.white)) markPosition(x + 1, y - 1);
                if (!obstacle8.color.equals(PlayerColor.white)) markPosition(x, y - 1);
            } else if (x == 7 && y == 0) {
                obstacle2 = getIcon(x - 1, y);
                obstacle3 = getIcon(x - 1, y + 1);
                obstacle4 = getIcon(x, y + 1);
                if (!obstacle2.color.equals(PlayerColor.white)) markPosition(x - 1, y);
                if (!obstacle3.color.equals(PlayerColor.white)) markPosition(x - 1, y + 1);
                if (!obstacle4.color.equals(PlayerColor.white)) markPosition(x, y + 1);
            } else if (x == 7 && y == 7) {
                obstacle1 = getIcon(x - 1, y - 1);
                obstacle2 = getIcon(x - 1, y);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle1.color.equals(PlayerColor.white)) markPosition(x - 1, y - 1);
                if (!obstacle2.color.equals(PlayerColor.white)) markPosition(x - 1, y);
                if (!obstacle8.color.equals(PlayerColor.white)) markPosition(x, y - 1);
            } else if (x == 0 && !(y == 0) && !(y == 7)) {
                obstacle4 = getIcon(x, y + 1);
                obstacle5 = getIcon(x + 1, y + 1);
                obstacle6 = getIcon(x + 1, y);
                obstacle7 = getIcon(x + 1, y - 1);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle4.color.equals(PlayerColor.white)) markPosition(x, y + 1);
                if (!obstacle5.color.equals(PlayerColor.white)) markPosition(x + 1, y + 1);
                if (!obstacle6.color.equals(PlayerColor.white)) markPosition(x + 1, y);
                if (!obstacle7.color.equals(PlayerColor.white)) markPosition(x + 1, y - 1);
                if (!obstacle8.color.equals(PlayerColor.white)) markPosition(x, y - 1);
            } else if (x == 7 && !(y == 0) && !(y == 7)) {
                obstacle1 = getIcon(x - 1, y - 1);
                obstacle2 = getIcon(x - 1, y);
                obstacle3 = getIcon(x - 1, y + 1);
                obstacle4 = getIcon(x, y + 1);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle1.color.equals(PlayerColor.white)) markPosition(x - 1, y - 1);
                if (!obstacle2.color.equals(PlayerColor.white)) markPosition(x - 1, y);
                if (!obstacle3.color.equals(PlayerColor.white)) markPosition(x - 1, y + 1);
                if (!obstacle4.color.equals(PlayerColor.white)) markPosition(x, y + 1);
                if (!obstacle8.color.equals(PlayerColor.white)) markPosition(x, y - 1);
            } else if (y == 0 && !(x == 0) && !(x == 7)) {
                obstacle2 = getIcon(x - 1, y);
                obstacle3 = getIcon(x - 1, y + 1);
                obstacle4 = getIcon(x, y + 1);
                obstacle5 = getIcon(x + 1, y + 1);
                obstacle6 = getIcon(x + 1, y);
                if (!obstacle2.color.equals(PlayerColor.white)) markPosition(x - 1, y);
                if (!obstacle3.color.equals(PlayerColor.white)) markPosition(x - 1, y + 1);
                if (!obstacle4.color.equals(PlayerColor.white)) markPosition(x, y + 1);
                if (!obstacle5.color.equals(PlayerColor.white)) markPosition(x + 1, y + 1);
                if (!obstacle6.color.equals(PlayerColor.white)) markPosition(x + 1, y);
            } else if (y == 7 && !(x == 0) && !(x == 7)) {
                obstacle1 = getIcon(x - 1, y - 1);
                obstacle2 = getIcon(x - 1, y);
                obstacle6 = getIcon(x + 1, y);
                obstacle7 = getIcon(x + 1, y - 1);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle1.color.equals(PlayerColor.white)) markPosition(x - 1, y - 1);
                if (!obstacle2.color.equals(PlayerColor.white)) markPosition(x - 1, y);
                if (!obstacle6.color.equals(PlayerColor.white)) markPosition(x + 1, y);
                if (!obstacle7.color.equals(PlayerColor.white)) markPosition(x + 1, y - 1);
                if (!obstacle8.color.equals(PlayerColor.white)) markPosition(x, y - 1);
            } else {
                obstacle1 = getIcon(x - 1, y - 1);
                obstacle2 = getIcon(x - 1, y);
                obstacle3 = getIcon(x - 1, y + 1);
                obstacle4 = getIcon(x, y + 1);
                obstacle5 = getIcon(x + 1, y + 1);
                obstacle6 = getIcon(x + 1, y);
                obstacle7 = getIcon(x + 1, y - 1);
                obstacle8 = getIcon(x, y - 1);
                if (!obstacle1.color.equals(PlayerColor.white)) markPosition(x - 1, y - 1);
                if (!obstacle2.color.equals(PlayerColor.white)) markPosition(x - 1, y);
                if (!obstacle3.color.equals(PlayerColor.white)) markPosition(x - 1, y + 1);
                if (!obstacle4.color.equals(PlayerColor.white)) markPosition(x, y + 1);
                if (!obstacle5.color.equals(PlayerColor.white)) markPosition(x + 1, y + 1);
                if (!obstacle6.color.equals(PlayerColor.white)) markPosition(x + 1, y);
                if (!obstacle7.color.equals(PlayerColor.white)) markPosition(x + 1, y - 1);
                if (!obstacle8.color.equals(PlayerColor.white)) markPosition(x, y - 1);
            }
        }

        public void checkBlackPawn(int x, int y)
        {
            boolean attackable = true;
            Piece target1 = new Piece();
            Piece target2 = new Piece();

            if (x == 7) return;
            else if (x != 6 && y == 0) {
                target2 = getIcon(x + 1, y + 1);
                if (target2.color.equals(PlayerColor.white)) markPosition(x + 1, y + 1);
                else attackable = false;
                if (!attackable) return;
            } else if (x != 6 && y == 7) {
                target1 = getIcon(x + 1, y - 1);
                if (target1.color.equals(PlayerColor.white)) markPosition(x + 1, y - 1);
                else attackable = false;
                if (!attackable) return;
            } else if (x == 6) {
                if (y == 0) {
                    target2 = getIcon(x + 1, y + 1);
                    if (target2.color.equals(PlayerColor.white)) markPosition(x + 1, y + 1);
                    else attackable = false;
                    if (!attackable) return;
                } else if (y == 7) {
                    target1 = getIcon(x + 1, y - 1);
                    if (target1.color.equals(PlayerColor.white)) markPosition(x + 1, y - 1);
                    else attackable = false;
                    if (!attackable) return;
                } else {
                    target1 = getIcon(x + 1, y - 1);
                    target2 = getIcon(x + 1, y + 1);
                    if (target1.color.equals(PlayerColor.white) || target2.color.equals(PlayerColor.white)) {
                        if (target1.color.equals(PlayerColor.white) && target2.color.equals(PlayerColor.white)) {
                            markPosition(x + 1, y - 1);
                            markPosition(x + 1, y + 1);
                        } else if (target1.color.equals(PlayerColor.white) && !target2.color.equals(PlayerColor.white))
                            markPosition(x + 1, y - 1);
                        else if (!target1.color.equals(PlayerColor.white) && target2.color.equals(PlayerColor.white))
                            markPosition(x + 1, y + 1);
                    } else attackable = false;
                    if (!attackable) return;
                }
            } else {
                target1 = getIcon(x + 1, y - 1);
                target2 = getIcon(x + 1, y + 1);
                if (target1.color.equals(PlayerColor.white) || target2.color.equals(PlayerColor.white)) {
                    if (target1.color.equals(PlayerColor.white) && target2.color.equals(PlayerColor.white)) {
                        markPosition(x + 1, y - 1);
                        markPosition(x + 1, y + 1);
                    } else if (target1.color.equals(PlayerColor.white) && !target2.color.equals(PlayerColor.white))
                        markPosition(x + 1, y - 1);
                    else if (!target1.color.equals(PlayerColor.white) && target2.color.equals(PlayerColor.white))
                        markPosition(x + 1, y + 1);
                } else attackable = false;
                if (!attackable) return;
            }
        }

        public void checkWhitePawn(int x, int y)
        {
            boolean attackable = true;
            Piece target1 = new Piece();
            Piece target2 = new Piece();

            if (x == 0) return;
            else if (x != 1 && y == 0) {
                target2 = getIcon(x - 1, y + 1);
                if (target2.color.equals(PlayerColor.black)) markPosition(x - 1, y + 1);
                else attackable = false;
                if (!attackable) return;
            } else if (x != 1 && y == 7) {
                target1 = getIcon(x - 1, y - 1);
                if (target1.color.equals(PlayerColor.black)) markPosition(x - 1, y - 1);
                else attackable = false;
                if (!attackable) return;
            } else if (x == 1) {
                if (y == 0) {
                    target2 = getIcon(x - 1, y + 1);
                    if (target2.color.equals(PlayerColor.black)) markPosition(x - 1, y + 1);
                    else attackable = false;
                    if (!attackable) return;
                } else if (y == 7) {
                    target1 = getIcon(x - 1, y - 1);
                    if (target1.color.equals(PlayerColor.black)) markPosition(x - 1, y - 1);
                    else attackable = false;
                    if (!attackable) return;
                } else {
                    target1 = getIcon(x - 1, y - 1);
                    target2 = getIcon(x - 1, y + 1);

                    if (target1.color.equals(PlayerColor.black) || target2.color.equals(PlayerColor.black)) {
                        if (target1.color.equals(PlayerColor.black) && target2.color.equals(PlayerColor.black)) {
                            markPosition(x - 1, y - 1);
                            markPosition(x - 1, y + 1);
                        } else if (target1.color.equals(PlayerColor.black) && !target2.color.equals(PlayerColor.black))
                            markPosition(x - 1, y - 1);
                        else if (!target1.color.equals(PlayerColor.black) && target2.color.equals(PlayerColor.black))
                            markPosition(x - 1, y + 1);
                    } else attackable = false;

                    if (!attackable) return;
                }
            } else {
                target1 = getIcon(x - 1, y - 1);
                target2 = getIcon(x - 1, y + 1);
                if (target1.color.equals(PlayerColor.black) || target2.color.equals(PlayerColor.black)) {
                    if (target1.color.equals(PlayerColor.black) && target2.color.equals(PlayerColor.black)) {
                        markPosition(x - 1, y - 1);
                        markPosition(x - 1, y + 1);
                    } else if (target1.color.equals(PlayerColor.black) && !target2.color.equals(PlayerColor.black))
                        markPosition(x - 1, y - 1);
                    else if (!target1.color.equals(PlayerColor.black) && target2.color.equals(PlayerColor.black))
                        markPosition(x - 1, y + 1);
                } else attackable = false;

                if (!attackable) return;
            }
        }

        public void unmarkAll() {
            for (int k = 0; k < 8; k++) {
                for (int l = 0; l < 8; l++) unmarkPosition(k, l);
            }
        }

        public boolean isTherePink() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (chessBoardSquares[j][i].getBackground().equals(Color.pink)) return true;
                }
            }
            return false;
        }

        public String check1(boolean isBlack) {
            Piece blackKing = new Piece();
            Piece whiteKing = new Piece();
            int kingX = 0, kingY = 0;
            String inpt = "";

            if (isBlack) {

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        whiteKing = chessBoardStatus[j][i];
                        if (whiteKing.type.equals(PieceType.king) && whiteKing.color.equals(PlayerColor.white)) {
                            kingX = i;
                            kingY = j;
                        }
                    }
                }
                inpt = check2(true, kingX, kingY);
                if(check) isCheckmate(true,kingX,kingY);
                if(checkmate) inpt = "WHITE\'s turn/checkmate";
                return inpt;
            } else {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        blackKing = chessBoardStatus[j][i];
                        if (blackKing.type.equals(PieceType.king) && blackKing.color.equals(PlayerColor.black)) {
                            kingX = i;
                            kingY = j;
                        }
                    }
                }
                inpt = check2(false, kingX, kingY);
                if(check) isCheckmate(false,kingX,kingY);
                if(checkmate) inpt = "BLACK\'s turn/checkmate";
                return inpt;
            }
        }

        public String check2(boolean isWhite, int kingX, int kingY) {
            Piece checker = new Piece();
            String inpt = "";
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    checker = chessBoardStatus[j][i];
                    if (isWhite) {
                        if (checker.color.equals(PlayerColor.black)) {

                            if (checker.type.equals(PieceType.king)) moveBlackKing(i, j);
                            if (checker.type.equals(PieceType.queen)) {
                                moveBlackRook(i, j);
                                moveBlackBishop(i, j);
                            }
                            if (checker.type.equals(PieceType.rook)) moveBlackRook(i, j);
                            if (checker.type.equals(PieceType.bishop)) moveBlackBishop(i, j);
                            if (checker.type.equals(PieceType.knight)) moveBlackKnight(i, j);
                            if (checker.type.equals(PieceType.pawn)) checkBlackPawn(i, j);

                        }
                    } else {
                        if (checker.color.equals(PlayerColor.white)) {
                            if (checker.type.equals(PieceType.king)) moveWhiteKing(i, j);
                            if (checker.type.equals(PieceType.queen)) {
                                moveWhiteRook(i, j);
                                moveWhiteBishop(i, j);
                            }
                            if (checker.type.equals(PieceType.rook)) moveWhiteRook(i, j);
                            if (checker.type.equals(PieceType.bishop)) moveWhiteBishop(i, j);
                            if (checker.type.equals(PieceType.knight)) moveWhiteKnight(i, j);
                            if (checker.type.equals(PieceType.pawn)) checkWhitePawn(i, j);

                        }
                    }
                }
            }

            if (chessBoardSquares[kingY][kingX].getBackground().equals(Color.pink)) {
                check = true;
                if (isWhite) inpt = "WHITE\'s turn/check";
                else inpt = "BLACK\'s turn/check";
                //setStatus(inpt);
                unmarkAll();
            } else check = false;

            unmarkAll();
            return inpt;
        }

        public void isCheckmate(boolean isWhite, int kingX, int kingY){
            boolean [] inpts = new boolean [8];
            for(int i = 0 ; i < 8 ; i++){
                inpts[i] = false;
            }

            if(isWhite){
                inpts[0] = subCheck(true, kingX - 1, kingY -1,kingX,kingY);
                inpts[1] = subCheck(true, kingX - 1, kingY,kingX,kingY);
                inpts[2] = subCheck(true, kingX - 1, kingY +1,kingX,kingY);
                inpts[3] = subCheck(true, kingX, kingY +1,kingX,kingY);
                inpts[4] = subCheck(true, kingX, kingY -1,kingX,kingY);
                inpts[5] = subCheck(true, kingX +1, kingY -1,kingX,kingY);
                inpts[6] = subCheck(true, kingX +1, kingY,kingX,kingY);
                inpts[7] = subCheck(true, kingX +1, kingY +1,kingX,kingY);

                for(int i = 0 ; i < 8 ; i++){
                    if(!inpts[i]){
                        checkmate = false;
                        return;
                    }
                }
                checkmate = true;
            }
            else{
                inpts[0] = subCheck(false, kingX - 1, kingY -1,kingX,kingY);
                inpts[1] = subCheck(false, kingX - 1, kingY,kingX,kingY);
                inpts[2] = subCheck(false, kingX - 1, kingY +1,kingX,kingY);
                inpts[3] = subCheck(false, kingX, kingY +1,kingX,kingY);
                inpts[4] = subCheck(false, kingX, kingY -1,kingX,kingY);
                inpts[5] = subCheck(false, kingX +1, kingY -1,kingX,kingY);
                inpts[6] = subCheck(false, kingX +1, kingY,kingX,kingY);
                inpts[7] = subCheck(false, kingX +1, kingY +1,kingX,kingY);

                for(int i = 0 ; i < 8 ; i++){
                    if(!inpts[i]){
                        checkmate = false;
                        return;
                    }
                }
                checkmate = true;
            }
        }

        public boolean subCheck(boolean isWhite, int kingX, int kingY, int origX, int origY){
            Piece checker;
            Piece none = new Piece(PlayerColor.none, PieceType.none);
            Piece king;
            Piece tmp;

            moveBlackKing(origX,origY);
            if(!(0<kingX && 8>kingX && 0<kingY && 8>kingY)){
                unmarkAll();
                return true;
            }
            if(!chessBoardSquares[kingY][kingX].getBackground().equals(Color.pink)){
                unmarkAll();
                return true;
            }
            unmarkAll();

            tmp = getIcon(kingX,kingY);
            if(isWhite) king = new Piece(PlayerColor.white,PieceType.king);
            else king = new Piece(PlayerColor.black,PieceType.king);
            setIcon(kingX,kingY,king);
            setIcon(origX,origY,none);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    checker = chessBoardStatus[j][i];
                    if (isWhite) {
                        if (checker.color.equals(PlayerColor.black)) {

                            if (checker.type.equals(PieceType.king)) moveBlackKing(i, j);
                            if (checker.type.equals(PieceType.queen)) {
                                moveBlackRook(i, j);
                                moveBlackBishop(i, j);
                            }
                            if (checker.type.equals(PieceType.rook)) moveBlackRook(i, j);
                            if (checker.type.equals(PieceType.bishop)) moveBlackBishop(i, j);
                            if (checker.type.equals(PieceType.knight)) moveBlackKnight(i, j);
                            if (checker.type.equals(PieceType.pawn)) checkBlackPawn(i, j);

                        }
                    } else {
                        if (checker.color.equals(PlayerColor.white)) {
                            if (checker.type.equals(PieceType.king)) moveWhiteKing(i, j);
                            if (checker.type.equals(PieceType.queen)) {
                                moveWhiteRook(i, j);
                                moveWhiteBishop(i, j);
                            }
                            if (checker.type.equals(PieceType.rook)) moveWhiteRook(i, j);
                            if (checker.type.equals(PieceType.bishop)) moveWhiteBishop(i, j);
                            if (checker.type.equals(PieceType.knight)) moveWhiteKnight(i, j);
                            if (checker.type.equals(PieceType.pawn)) checkWhitePawn(i, j);


                        }
                    }
                }
            }

            if (chessBoardSquares[kingY][kingX].getBackground().equals(Color.pink)){
                unmarkAll();
                setIcon(kingX,kingY,tmp);
                setIcon(origX,origY,king);
                return true;
            }
            else{
                unmarkAll();
                setIcon(kingX,kingY,tmp);
                setIcon(origX,origY,king);
                return false;
            }
        }
    }

    void onInitiateBoard() {
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) setIcon(i, j, new Piece());
        }
        selX = -1;
        setStatus("BLACK\'s turn");
        checkmate = false;
        check = false;
        setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
        setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
        setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
        setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
        setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
        setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
        setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
        setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
        for(int i=0;i<8;i++){
            setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
            setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
        }
        setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
        setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
        setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
        setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
        setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
        setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
        setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
        setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) unmarkPosition(i, j);
        }
    }

}
