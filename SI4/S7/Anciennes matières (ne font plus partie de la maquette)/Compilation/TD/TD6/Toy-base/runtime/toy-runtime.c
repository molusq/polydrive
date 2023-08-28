/*
 * toy-runtime.c        -- The Toy programming language runtime library
 *
 * Copyright © 2015-2017 Erick Gallesio - I3S-CNRS/Polytech Nice-Sophia <eg@unice.fr>
 *
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307,
 * USA.
 *
 *           Author: Erick Gallesio [eg@unice.fr]
 *    Creation date:  2-Jul-2015 09:44 (eg)
 * Last file update: 16-Dec-2017 22:20 (eg)
 */


#include <stdio.h>
#include <stdlib.h>
#include "toy-runtime.h"


// ----------------------------------------------------------------------
//
//                       Print functions
//
// ----------------------------------------------------------------------

int power(int o, int p) {
    int res = o;
    if (p == 0) return 1;
    for (int i = 1; i < p; i++) {
        res *= o;
    }
    return res;
}

void _toy_print_int(int o) { printf("%d", o); }

void _toy_print_bool(char o) { printf("%s", o ? "true" : "false"); }

void _toy_print_string(_toy_string o) { printf("%s", o); }

void _toy_print_powint(int o, int p) { printf("%d", power(o, p)); }

