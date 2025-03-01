namespace Reloj_Checador
{
    partial class Form1
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.txtEmpleadoId = new System.Windows.Forms.TextBox();
            this.FechaLb = new System.Windows.Forms.Label();
            this.HoraLb = new System.Windows.Forms.Label();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.btnRegistrar = new System.Windows.Forms.Button();
            this.btnAsistencias = new System.Windows.Forms.Button();
            this.rbSalida = new System.Windows.Forms.RadioButton();
            this.rbEntrada = new System.Windows.Forms.RadioButton();
            this.pictureBox = new System.Windows.Forms.PictureBox();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // txtEmpleadoId
            // 
            this.txtEmpleadoId.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(21)))), ((int)(((byte)(36)))));
            this.txtEmpleadoId.Font = new System.Drawing.Font("Arial Narrow", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtEmpleadoId.ForeColor = System.Drawing.Color.White;
            this.txtEmpleadoId.Location = new System.Drawing.Point(24, 193);
            this.txtEmpleadoId.Multiline = true;
            this.txtEmpleadoId.Name = "txtEmpleadoId";
            this.txtEmpleadoId.Size = new System.Drawing.Size(304, 37);
            this.txtEmpleadoId.TabIndex = 4;
            this.txtEmpleadoId.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // FechaLb
            // 
            this.FechaLb.AutoSize = true;
            this.FechaLb.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(21)))), ((int)(((byte)(36)))));
            this.FechaLb.Font = new System.Drawing.Font("Modern No. 20", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.FechaLb.ForeColor = System.Drawing.Color.Snow;
            this.FechaLb.Location = new System.Drawing.Point(161, 46);
            this.FechaLb.Name = "FechaLb";
            this.FechaLb.Size = new System.Drawing.Size(94, 34);
            this.FechaLb.TabIndex = 20;
            this.FechaLb.Text = "Fecha";
            // 
            // HoraLb
            // 
            this.HoraLb.AutoSize = true;
            this.HoraLb.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(21)))), ((int)(((byte)(36)))));
            this.HoraLb.Font = new System.Drawing.Font("Modern No. 20", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.HoraLb.ForeColor = System.Drawing.Color.Snow;
            this.HoraLb.Location = new System.Drawing.Point(172, 96);
            this.HoraLb.Name = "HoraLb";
            this.HoraLb.Size = new System.Drawing.Size(83, 34);
            this.HoraLb.TabIndex = 21;
            this.HoraLb.Text = "Hora";
            // 
            // timer1
            // 
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // btnRegistrar
            // 
            this.btnRegistrar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(125)))), ((int)(((byte)(0)))));
            this.btnRegistrar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnRegistrar.Font = new System.Drawing.Font("Arial Narrow", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnRegistrar.Location = new System.Drawing.Point(47, 239);
            this.btnRegistrar.Name = "btnRegistrar";
            this.btnRegistrar.Size = new System.Drawing.Size(106, 33);
            this.btnRegistrar.TabIndex = 23;
            this.btnRegistrar.Text = "Registrar ";
            this.btnRegistrar.UseVisualStyleBackColor = false;
            this.btnRegistrar.Click += new System.EventHandler(this.btnRegistrar_Click);
            // 
            // btnAsistencias
            // 
            this.btnAsistencias.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(125)))), ((int)(((byte)(0)))));
            this.btnAsistencias.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnAsistencias.Font = new System.Drawing.Font("Arial Narrow", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnAsistencias.Location = new System.Drawing.Point(186, 239);
            this.btnAsistencias.Name = "btnAsistencias";
            this.btnAsistencias.Size = new System.Drawing.Size(126, 33);
            this.btnAsistencias.TabIndex = 24;
            this.btnAsistencias.Text = "Ver Asistencias";
            this.btnAsistencias.UseVisualStyleBackColor = false;
            this.btnAsistencias.Click += new System.EventHandler(this.btnAsistencias_Click);
            // 
            // rbSalida
            // 
            this.rbSalida.AutoSize = true;
            this.rbSalida.Font = new System.Drawing.Font("Arial Narrow", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rbSalida.ForeColor = System.Drawing.Color.White;
            this.rbSalida.Location = new System.Drawing.Point(186, 163);
            this.rbSalida.Name = "rbSalida";
            this.rbSalida.Size = new System.Drawing.Size(121, 24);
            this.rbSalida.TabIndex = 25;
            this.rbSalida.TabStop = true;
            this.rbSalida.Text = "Registrar Salida";
            this.rbSalida.UseVisualStyleBackColor = true;
            // 
            // rbEntrada
            // 
            this.rbEntrada.AutoSize = true;
            this.rbEntrada.Font = new System.Drawing.Font("Arial Narrow", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rbEntrada.ForeColor = System.Drawing.Color.White;
            this.rbEntrada.Location = new System.Drawing.Point(24, 163);
            this.rbEntrada.Name = "rbEntrada";
            this.rbEntrada.Size = new System.Drawing.Size(129, 24);
            this.rbEntrada.TabIndex = 26;
            this.rbEntrada.TabStop = true;
            this.rbEntrada.Text = "Registrar Entrada";
            this.rbEntrada.UseVisualStyleBackColor = true;
            // 
            // pictureBox
            // 
            this.pictureBox.Image = global::Reloj_Checador.Properties.Resources.LogoReloj;
            this.pictureBox.Location = new System.Drawing.Point(334, 169);
            this.pictureBox.Name = "pictureBox";
            this.pictureBox.Size = new System.Drawing.Size(103, 103);
            this.pictureBox.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox.TabIndex = 22;
            this.pictureBox.TabStop = false;
            // 
            // pictureBox2
            // 
            this.pictureBox2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(21)))), ((int)(((byte)(36)))));
            this.pictureBox2.Location = new System.Drawing.Point(18, 24);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(419, 126);
            this.pictureBox2.TabIndex = 19;
            this.pictureBox2.TabStop = false;
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(456, 24);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(352, 248);
            this.dataGridView1.TabIndex = 27;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(21)))), ((int)(((byte)(97)))), ((int)(((byte)(109)))));
            this.ClientSize = new System.Drawing.Size(828, 292);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.rbEntrada);
            this.Controls.Add(this.rbSalida);
            this.Controls.Add(this.btnAsistencias);
            this.Controls.Add(this.btnRegistrar);
            this.Controls.Add(this.pictureBox);
            this.Controls.Add(this.HoraLb);
            this.Controls.Add(this.FechaLb);
            this.Controls.Add(this.pictureBox2);
            this.Controls.Add(this.txtEmpleadoId);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.TextBox txtEmpleadoId;
        private System.Windows.Forms.PictureBox pictureBox2;
        private System.Windows.Forms.Label FechaLb;
        private System.Windows.Forms.Label HoraLb;
        private System.Windows.Forms.PictureBox pictureBox;
        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.Button btnRegistrar;
        private System.Windows.Forms.Button btnAsistencias;
        private System.Windows.Forms.RadioButton rbSalida;
        private System.Windows.Forms.RadioButton rbEntrada;
        private System.Windows.Forms.DataGridView dataGridView1;
    }
}

