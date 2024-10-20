import matplotlib.pyplot as plt
import networkx as nx
from PIL import Image
# 定義讀取檔案的函數
def read_patterns(filename):
    patterns = []
    pids = []
    gains = []

    with open(filename, 'r') as file:
        current_patterns = []
        current_pids = []
        current_gains = []

        for line in file:
            # 跳過空行或分隔符
            if line.strip() == "" or line.startswith("---"):
                if current_patterns:  # 如果有暫存的資料，則將其加入最終的結果
                    patterns.append(current_patterns)
                    pids.append(current_pids)
                    gains.append(current_gains)
                    
                # 重置暫存
                current_patterns = []
                current_pids = []
                current_gains = []
                continue

            # 解析行
            parts = line.strip().split('\t')
            print(parts)
            if len(parts) == 3:
                pattern_str, pid_str, gain_str = parts

                # 處理 pattern
                pattern = ""
                for i in pattern_str:
                    if i != '[' and i != ']' and i != ',' and i != ' ':
                        pattern += i
                current_patterns.append(pattern)

                # 處理 PIDs
                pid = ""
                for i in pattern_str:
                    if i != '[' and i != ']' and i != ',' and i != ' ':
                        pid += i
                current_pids.append(pid)

                # 處理 gain
                gain = int(gain_str)
                current_gains.append(gain)

        # 添加最後一組
        if current_patterns:
            patterns.append(current_patterns)
            pids.append(current_pids)
            gains.append(current_gains)

    return patterns, pids, gains

filename = 'largerMGT.txt'
patterns, pids, gains = read_patterns(filename)

# print("Patterns:", patterns)
# print("PIDs:", pids)
# print("Gains:", gains)


G = nx.DiGraph()
level = 1
png_file = []
for current_level in range ( 1, level + 1 ):
    positions_list = []
    positions = {}
    for i in range ( current_level ):
        temp_list = []
        for j in range ( len(patterns[i]) ):
            temp_list.append ((j, current_level - i + 2))
        temp_dit = dict(zip(patterns[i], temp_list))
        positions.update(temp_dit)

    # positions = {
    #     "B": (0, 4), "A": (1, 4), "E": (2, 4), "C": (3, 4), "D": (4, 4), "G": (5, 4), "F": (6, 4),
    #     "BC": (0, 3), "BA": (1, 3), "EC": (2, 3), "ED": (3, 3), "CG": (4, 3), "DF": (5, 3),
    # }

    labels = {}
    for i in range ( current_level ):
        temp_dit = dict(zip(patterns[i], patterns[i]))
        labels.update(temp_dit)


    for node, pos in positions.items():
        G.add_node(node, pos=pos)

    edges = []
    for i in range ( current_level - 1 ):
        for j in range ( len(patterns[i]) ):
            for k in range ( len(patterns[i+1]) ):
                compare_setA = set(patterns[i][j])
                compare_setB = set(patterns[i+1][k])
                if compare_setA <= compare_setB:
                    edges.append((patterns[i][j], patterns[i+1][k]))
                
    print(edges)

    G.add_edges_from(edges)

    plt.figure(figsize=(60, 5))
    nx.draw(G, pos=positions, with_labels=True, labels=labels, node_size=60000, node_color='lightgrey', font_size=80, font_color='black', font_weight='bold', width=4)
    plt.title("Graph Representation")
    output_file = "output_MGT_image.png"
    png_file.append(output_file)
    plt.savefig( output_file , format='png')
