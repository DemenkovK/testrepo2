TARGETS = console-setup mountkernfs.sh resolvconf ufw hostname.sh apparmor screen-cleanup plymouth-log udev keyboard-setup cryptdisks cryptdisks-early networking urandom hwclock.sh iscsid open-iscsi checkroot.sh lvm2 checkfs.sh mountdevsubfs.sh checkroot-bootclean.sh bootmisc.sh mountall.sh mountall-bootclean.sh kmod mountnfs.sh mountnfs-bootclean.sh procps
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: mountkernfs.sh urandom resolvconf procps
urandom: hwclock.sh
hwclock.sh: mountdevsubfs.sh
iscsid: networking
open-iscsi: networking iscsid
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountall-bootclean.sh mountnfs-bootclean.sh udev
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
kmod: checkroot.sh
mountnfs.sh: networking
mountnfs-bootclean.sh: mountnfs.sh
procps: mountkernfs.sh udev
