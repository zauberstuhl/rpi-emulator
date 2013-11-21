#!/bin/bash
#
# Boot the Raspberry Pi in QEMU
#
#    Usage: boot [image]
#
#        where [image] is a filesystem image to boot from. This is optional: if it is not
#        given then it defaults to "archlinux-13-0-6-2012.img" which must be present in
#        the current working directory.
#
# JL 2012-08-03
#

qemu-system-arm -machine versatilepb                      \
	        -cpu arm1176                              \
		-m 256                                    \
                -no-reboot                                \
        	-serial stdio                             \
                -kernel kernel-arch-qemu                       \
		-append "root=/dev/sda2 panic=1 rw"       \
		-hda ${1:-archlinux-hf-2012-09-18.img}    \
    -net nic,vlan=0 -net tap,vlan=0,ifname=tap0,script=./qemu-ifup \
    -hdb data_disk.raw
