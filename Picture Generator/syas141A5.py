
from tkinter import *

#-------------------------------------------
#-------------------------------------------
# Part 1: main() function
#-------------------------------------------
def main():
    """
    colour_filename = "Image1_Colours.txt" #Use these two statements to see the third image
    filename = "Image1.txt"

    colour_filename = "Image2_Colours.txt" #Use these two statements to see the second image
    filename = "Image2.txt"    
    """

    colour_filename = "Image3_Colours.txt"
    filename = "Image3.txt"
    
    list_of_lines = get_lines_from_file(filename) #Part 1.
    size, canvas_width, canvas_height = get_size_width_height(list_of_lines.pop(0)) #Part 2.

    start_left = size
    start_top = size

    list_of_tuple_lists = get_list_of_lists(list_of_lines) #Part 3.
    colour_dictionary = get_colour_dictionary(colour_filename) #Part 4.

    window = Tk()
    window.title("A5 by")
    geometry_string = str(canvas_width)+"x"+str(canvas_height)+"+10+20"
    window.geometry(geometry_string)
    a_canvas = Canvas(window)
    a_canvas.config(background="linen")
    a_canvas.pack(fill=BOTH, expand = True) #Canvas fills the whole window

    draw_pattern(a_canvas, list_of_tuple_lists, colour_dictionary, size, start_left, start_top) #Part 5.

    window.mainloop()

#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
# Part 2: Read the pattern strings from file
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
def get_lines_from_file(file):
    file_variable = open(file, "r")
    content = file_variable.readlines()
    for i in range(0, len(content)):
        content[i] = content[i].strip("\n")
    return content
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
# Part 3: Return tuple of size, width and height
# obtained from the parameter string
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
def get_size_width_height(string):
    list1 = string.split()
    for i in range(len(list1)):
        list1[i] = int(list1[i])
    return tuple(list1)

#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
# Part 4: Get list of tuple-lists
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
def get_list_of_lists(list_of_strings):
    new_list2 = []
    for num in list_of_strings:
        abc = num.split()
        new_list = []
        for i in range(0, len(abc), 2):
            new_list1 = []
            new_list1.append(abc[i])
            new_list1.append(int(abc[i+1]))
            new_list.append(tuple(new_list1))
        new_list2.append(new_list)
    return new_list2

#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
# Part 5: Get dictionary of colours
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
def get_colour_dictionary(file):
    file_variable = open(file, 'r')
    content = file_variable.read()
    list1 = content.split()
    colour_dict = {}
    for i in range(0, len(list1), 2):
        colour_dict[list1[i]] = list1[i+1]
    return colour_dict
    
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
# Part 6: Draw the pattern
#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
def draw_pattern(a_canvas, pattern_list, colour_dictionary, size, left, top):
    y =top
    for element in pattern_list:
        current_pattern = element
        x = left
        for pattern in current_pattern:
            a_canvas.create_rectangle(x, y, x+(size * pattern[1]), y + size, fill = colour_dictionary[pattern[0]], outline = "")
            x = x + size * pattern[1]
        y = y + size
main()


