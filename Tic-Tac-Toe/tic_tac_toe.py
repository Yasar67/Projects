tic_tac_toe_board = {'1': ' ' , '2': ' ' , '3': ' ' , '4': ' ' , '5': ' ' , '6': ' ' , '7': ' ' , '8': ' ' , '9': ' ' }

values = []

for key in tic_tac_toe_board:
    values.append(key)

def get_board(board):
    print(board['1'] + '|' + board['2'] + '|' + board['3'])
    print('-+-+-')
    print(board['4'] + '|' + board['5'] + '|' + board['6'])
    print('-+-+-')
    print(board['7'] + '|' + board['8'] + '|' + board['9'])

# Implementation of main() which includes all the functionality.
def game():

    count = 0
    player = 'X'


    for p in range(10):
        get_board(tic_tac_toe_board)
        print("It is your turn," + player + ".Which position?")

        move = input()        

        if tic_tac_toe_board[move] == ' ':
            tic_tac_toe_board[move] = player
            count += 1
        else:
            print("That position is already filled.\nWhich position?")
            continue

        # Check if anyone won after 5 moves.  
        if count >= 5:
            if tic_tac_toe_board['1'] == tic_tac_toe_board['2'] == tic_tac_toe_board['3'] != ' ': # across the top
                get_board(tic_tac_toe_board)
                print("\nThe game has finished.\n")                
                print(" **** Congratulations " +player + "!. You have won. ****")                
                break
            elif tic_tac_toe_board['4'] == tic_tac_toe_board['5'] == tic_tac_toe_board['6'] != ' ': # across the middle
                get_board(tic_tac_toe_board)
                print("\nThe game has finished.\n")                
                print(" **** Congratulations " +player + "!. You have won. ****")              
                break
            elif tic_tac_toe_board['7'] == tic_tac_toe_board['8'] == tic_tac_toe_board['8'] != ' ': # across the bottom
                get_board(tic_tac_toe_board)
                print("\nThe game has finished.\n")                
                print(" **** Congratulations " +player + "!. You have won. ****")    
                break
            elif tic_tac_toe_board['7'] == tic_tac_toe_board['5'] == tic_tac_toe_board['3'] != ' ': # diagonal
                get_board(tic_tac_toe_board)
                print("\nThe game has finished.\n")                
                print(" **** Congratulations " +player + "!. You have won. ****")    
                break
            elif tic_tac_toe_board['1'] == tic_tac_toe_board['5'] == tic_tac_toe_board['9'] != ' ': # diagonal
                get_board(tic_tac_toe_board)
                print("\nThe game has finished.\n")                
                print(" **** Congratulations " +player + "!. You have won. ****")    
                break 
            elif tic_tac_toe_board['1'] == tic_tac_toe_board['4'] == tic_tac_toe_board['7'] != ' ': # down the left side
                get_board(tic_tac_toe_board)
                print("\nThe game has finished.\n")                
                print(" **** Congratulations " +player + "!. You have won. ****")     
                break
            elif tic_tac_toe_board['2'] == tic_tac_toe_board['5'] == tic_tac_toe_board['8'] != ' ': # down the middle
                get_board(tic_tac_toe_board)
                print("\nThe game has finished.\n")                
                print(" **** Congratulations " +player + "!. You have won. ****")    
                break
            elif tic_tac_toe_board['3'] == tic_tac_toe_board['6'] == tic_tac_toe_board['9'] != ' ': # down the right side
                get_board(tic_tac_toe_board)
                print("\nThe game has finished.\n")                
                print(" **** Congratulations " +player + "!. You have won. ****")    
                break

        # After all positions are filled and there is no winner declare a draw.
        if count > 8:
            print("\nThe game has finished.\n")                         
            print("It is a Draw")

        # Changes player after every turn.
        if player =='X':
            player = 'O'
        else:
            player = 'X'        
    
    # Give the player an opportunity to start a new game.
    restart = input("Play Again?(y/n)")
    if restart == "y" or restart == "Y":  
        for value in values:
            tic_tac_toe_board[value] = " "

        game()

if __name__ == "__main__":
    game()
