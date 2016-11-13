namespace CityTravelService.Entity
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    [Table("TUKHOADICHVU")]
    public partial class TUKHOADICHVU
    {
        [Key]
        public int MaTuKhoaDichVu { get; set; }

        [Column("TuKhoaDichVu")]
        [StringLength(64)]
        public string TuKhoaDichVu1 { get; set; }

        public int? MaDichVu { get; set; }

        public virtual DICHVU DICHVU { get; set; }
    }
}
