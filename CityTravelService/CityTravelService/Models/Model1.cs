namespace CityTravelService.Entity
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Model1 : DbContext
    {
        public Model1()
            : base("name=Model1")
        {
        }

        public virtual DbSet<BINHLUAN> BINHLUANs { get; set; }
        public virtual DbSet<CHITIETDICHVU> CHITIETDICHVUs { get; set; }
        public virtual DbSet<DICHVU> DICHVUs { get; set; }
        public virtual DbSet<DULIEU> DULIEUx { get; set; }
        public virtual DbSet<DUONG> DUONGs { get; set; }
        public virtual DbSet<PHUONG> PHUONGs { get; set; }
        public virtual DbSet<QUANHUYEN> QUANHUYENs { get; set; }
        public virtual DbSet<sysdiagram> sysdiagrams { get; set; }
        public virtual DbSet<TAIKHOAN> TAIKHOANs { get; set; }
        public virtual DbSet<TENDIADIEM> TENDIADIEMs { get; set; }
        public virtual DbSet<TINHTHANH> TINHTHANHs { get; set; }
        public virtual DbSet<TUKHOADIADIEM> TUKHOADIADIEMs { get; set; }
        public virtual DbSet<TUKHOADICHVU> TUKHOADICHVUs { get; set; }
        public virtual DbSet<TUKHOADUONG> TUKHOADUONGs { get; set; }
        public virtual DbSet<TUKHOAPHUONG> TUKHOAPHUONGs { get; set; }
        public virtual DbSet<TUKHOAQUANHUYEN> TUKHOAQUANHUYENs { get; set; }
        public virtual DbSet<TUKHOATINHTHANH> TUKHOATINHTHANHs { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<BINHLUAN>()
                .Property(e => e.MaBinhLuan)
                .IsUnicode(false);

            modelBuilder.Entity<BINHLUAN>()
                .Property(e => e.Email)
                .IsUnicode(false);

            modelBuilder.Entity<CHITIETDICHVU>()
                .HasMany(e => e.DULIEUx)
                .WithMany(e => e.CHITIETDICHVUs)
                .Map(m => m.ToTable("CHITIET_DULIEU").MapLeftKey("MaChiTiet").MapRightKey("MaDuLieu"));

            modelBuilder.Entity<DICHVU>()
                .Property(e => e.Hinh)
                .IsUnicode(false);

            modelBuilder.Entity<DICHVU>()
                .HasMany(e => e.DULIEUx)
                .WithOptional(e => e.DICHVU)
                .WillCascadeOnDelete();

            modelBuilder.Entity<DICHVU>()
                .HasMany(e => e.TUKHOADICHVUs)
                .WithOptional(e => e.DICHVU)
                .WillCascadeOnDelete();

            modelBuilder.Entity<DULIEU>()
                .Property(e => e.MaBinhLuan)
                .IsUnicode(false);

            modelBuilder.Entity<DUONG>()
                .HasMany(e => e.DULIEUx)
                .WithOptional(e => e.DUONG)
                .WillCascadeOnDelete();

            modelBuilder.Entity<DUONG>()
                .HasMany(e => e.TUKHOADUONGs)
                .WithOptional(e => e.DUONG)
                .WillCascadeOnDelete();

            modelBuilder.Entity<PHUONG>()
                .HasMany(e => e.DULIEUx)
                .WithOptional(e => e.PHUONG)
                .WillCascadeOnDelete();

            modelBuilder.Entity<PHUONG>()
                .HasMany(e => e.TUKHOAPHUONGs)
                .WithOptional(e => e.PHUONG)
                .WillCascadeOnDelete();

            modelBuilder.Entity<QUANHUYEN>()
                .HasMany(e => e.DULIEUx)
                .WithOptional(e => e.QUANHUYEN)
                .WillCascadeOnDelete();

            modelBuilder.Entity<QUANHUYEN>()
                .HasMany(e => e.TUKHOAQUANHUYENs)
                .WithOptional(e => e.QUANHUYEN)
                .WillCascadeOnDelete();

            modelBuilder.Entity<TAIKHOAN>()
                .Property(e => e.Email)
                .IsUnicode(false);

            modelBuilder.Entity<TAIKHOAN>()
                .Property(e => e.MatKhau)
                .IsUnicode(false);

            modelBuilder.Entity<TAIKHOAN>()
                .Property(e => e.Hinh)
                .IsUnicode(false);

            modelBuilder.Entity<TENDIADIEM>()
                .HasMany(e => e.DULIEUx)
                .WithOptional(e => e.TENDIADIEM)
                .WillCascadeOnDelete();

            modelBuilder.Entity<TENDIADIEM>()
                .HasMany(e => e.TUKHOADIADIEMs)
                .WithOptional(e => e.TENDIADIEM)
                .WillCascadeOnDelete();

            modelBuilder.Entity<TINHTHANH>()
                .HasMany(e => e.DULIEUx)
                .WithOptional(e => e.TINHTHANH)
                .WillCascadeOnDelete();

            modelBuilder.Entity<TINHTHANH>()
                .HasMany(e => e.TUKHOATINHTHANHs)
                .WithOptional(e => e.TINHTHANH)
                .WillCascadeOnDelete();
        }
    }
}
