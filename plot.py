import matplotlib.pyplot as plt
import networkx as nx
from matplotlib.patches import FancyBboxPatch

# 建立有向圖
G = nx.DiGraph()

# 添加節點及其位置
positions = {
    "B": (0, 4), "A": (1, 4), "E": (2, 4), "C": (3, 4), "D": (4, 4), "G": (5, 4), "F": (6, 4),
    "BC": (0, 3), "BA": (1, 3), "EC": (2, 3), "ED": (3, 3), "CG": (4, 3), "DF": (5, 3),
}

# 添加邊
edges = [("B", "BC"), ("A", "BA"), ("E", "EC"), ("C", "CG"), ("D", "DF")]

G.add_edges_from(edges)

# 繪製基礎節點和邊
plt.figure(figsize=(12, 8))
ax = plt.gca()
nx.draw(G, pos=positions, with_labels=False, node_size=2000, node_color='white', edge_color='black', ax=ax)

# 自訂節點標籤，逐一添加多層內容
for node, (x, y) in positions.items():
    # 根據節點名稱定義背景形狀
    bbox = FancyBboxPatch((x - 0.15, y - 0.15), 0.3, 0.3, boxstyle="round,pad=0.2", color="lightblue", ec="black")
    ax.add_patch(bbox)

    # 定義節點內的多層文字內容
    if node == "B":
        text = "B\n$P_1, P_2, P_3, P_4$\n$2,400$"
    elif node == "A":
        text = "A\n$P_5, P_6, P_7, P_8$\n$1,800$"
    else:
        text = f"{node}"  # 單層內容

    # 將文字分為多行
    text_lines = text.split('\n')

    # 添加第一層標籤
    ax.text(x, y + 0.1, text_lines[0], fontsize=12, ha='center', va='center', fontweight='bold')  

    # 安全地添加第二層標籤（如果有）
    if len(text_lines) > 1:
        ax.text(x, y, text_lines[1], fontsize=10, ha='center', va='center', color='blue')

    # 安全地添加第三層標籤（如果有）
    if len(text_lines) > 2:
        ax.text(x, y - 0.1, text_lines[2], fontsize=10, ha='center', va='center', color='red')

plt.show()
