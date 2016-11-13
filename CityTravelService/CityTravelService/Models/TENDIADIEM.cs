namespace CityTravelService.Entity
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    [Table("TENDIADIEM")]
    public partial class TENDIADIEM
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public TENDIADIEM()
        {
            DULIEUx = new HashSet<DULIEU>();
            TUKHOADIADIEMs = new HashSet<TUKHOADIADIEM>();
        }

        [Key]
        public int MaTenDiaDiem { get; set; }

        [Column("TenDiaDiem")]
        [StringLength(128)]
        public string TenDiaDiem1 { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DULIEU> DULIEUx { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<TUKHOADIADIEM> TUKHOADIADIEMs { get; set; }
    }
}
