import tkinter as tk
from tkinter import messagebox, filedialog
import qrcode
from PIL import Image, ImageTk

qr_img = None
qr_photo = None

def generate_qr():
    global qr_img, qr_photo

    data = entry.get()
    if not data:
        messagebox.showwarning("Warning", "Enter text or URL")
        return

    qr = qrcode.QRCode(
        version=1,
        error_correction=qrcode.constants.ERROR_CORRECT_H,
        box_size=8,
        border=4
    )

    qr.add_data(data)
    qr.make(fit=True)

    qr_img = qr.make_image(fill_color="black", back_color="white").convert('RGB')

    try:
        logo = Image.open("logo.png") 

    
        qr_w, qr_h = qr_img.size
        logo_size = qr_w // 4
        logo = logo.resize((logo_size, logo_size))

        pos = ((qr_w - logo_size) // 2, (qr_h - logo_size) // 2)
        qr_img.paste(logo, pos)

    except:
        messagebox.showinfo("Note", "Logo not found! QR generated without logo.")

    preview = qr_img.resize((200, 200))
    qr_photo = ImageTk.PhotoImage(preview)
    preview_label.config(image=qr_photo)

def save_qr():
    if qr_img is None:
        messagebox.showerror("Error", "Generate QR first!")
        return

    file_path = filedialog.asksaveasfilename(defaultextension=".png")
    if file_path:
        qr_img.save(file_path)
        messagebox.showinfo("Saved", "QR Code Saved!")

def clear_all():
    entry.delete(0, tk.END)
    preview_label.config(image="")

root = tk.Tk()
root.title("QR Code Generator with Logo")
root.geometry("400x400")

tk.Label(root, text="Enter Text / URL:", font=("Arial", 12)).pack(pady=10)

entry = tk.Entry(root, width=35, font=("Arial", 12))
entry.pack(pady=5)

tk.Button(root, text="Generate QR", command=generate_qr,
          bg="green", fg="white").pack(pady=10)

tk.Button(root, text="Save QR", command=save_qr).pack(pady=5)

tk.Button(root, text="Clear", command=clear_all,
          bg="red", fg="white").pack(pady=5)

preview_label = tk.Label(root)
preview_label.pack(pady=15)

root.mainloop()