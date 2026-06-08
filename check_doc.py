#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
验证文档内容
"""

from docx import Document

def check_document():
    """检查文档中的2.4部分"""
    doc_path = '/workspace/实验室管理系统开发技术报告.docx'
    doc = Document(doc_path)
    
    print("=== 检查文档中的 2.4 部分 ===")
    found_24 = False
    for i, para in enumerate(doc.paragraphs):
        if '2.4' in para.text:
            found_24 = True
            print(f"段落 {i}: {para.text}")
        # 打印2.4附近的内容
        if found_24 and i < 300:
            if para.text.strip():
                print(f"段落 {i}: {para.text[:100]}")
        if i > 300:
            break

if __name__ == '__main__':
    check_document()
