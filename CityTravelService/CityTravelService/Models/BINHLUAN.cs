namespace CityTravelService.Entity
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using CityTravelService.Models;

    [Table("BINHLUAN")]
    public partial class BINHLUAN
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public BINHLUAN()
        {
            DULIEUx = new HashSet<DULIEU>();
        }

        [Key]
        [StringLength(20)]
        public string MaBinhLuan { get; set; }

        [StringLength(50)]
        public string Email { get; set; }

        [StringLength(500)]
        public string NoiDung { get; set; }

        public DateTime? ThoiGian { get; set; }

        public virtual TaiKhoan TAIKHOAN { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DULIEU> DULIEUx { get; set; }
    }
}
